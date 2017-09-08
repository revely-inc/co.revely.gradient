package com.revely.gradient.drawables

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log


/**
 * Created at 08/09/17
 *
 * @author rbenjami
 */
class GradientDrawable(
		type: Type = GradientDrawable.Type.LINEAR,
		colors: IntArray = intArrayOf(Color.WHITE, Color.BLACK),
		rotation: Float = 0.0f,
		tileMode: Shader.TileMode = Shader.TileMode.CLAMP,
		private var gradientAlpha: Int = 255,
		private var offsets: FloatArray? = null
) : Drawable()
{
	enum class Type
	{
		LINEAR, RADIAL, SWEEP
	}

	private val rotateMatrix: Matrix = Matrix()

	private var rebuildGradient: Boolean = true
	private var gradientPaint: Paint = Paint()
	private var centerInit = false
	private var centerX: Float = 0.0f
	private var centerY: Float = 0.0f

	var type: Type = type
		set(type)
		{
			field = type
			rebuild()
		}
	var colors: IntArray = colors
		set(colors)
		{
			field = colors
			rebuild()
		}
	var rotation: Float = rotation
		set(rotation)
		{
			field = rotation
			rebuild()
		}
	var tileMode: Shader.TileMode = tileMode
		set(tileMode)
		{
			field = tileMode
			rebuild()
		}

	private fun gradientPaint(): Paint?
	{
		if ( rebuildGradient )
		{
			val height = bounds.height()
			val width = bounds.width()
			val radius = Math.max(width, height) / 2.0f

			if (!centerInit)
			{
				centerX = width / 2.0f
				centerY = height / 2.0f
				centerInit = true
			}

			val x0 = 0.0f
			val y0 = height / 2.0f
			val x1 = width.toFloat()
			val y1 = height / 2.0f

			val gradient = when (type)
			{
				Type.LINEAR -> LinearGradient(x0, y0, x1, y1, colors, offsets, tileMode)
				Type.RADIAL -> RadialGradient(centerX, centerY, radius, colors, offsets, tileMode)
				Type.SWEEP -> SweepGradient(centerX, centerY, colors, offsets)
			}

			rotateMatrix.setRotate(rotation, centerX, centerY)
			gradient.setLocalMatrix(rotateMatrix)
			gradientPaint = Paint()
			gradientPaint.shader = gradient
			gradientPaint.alpha = gradientAlpha
			rebuildGradient = false
			return gradientPaint
		}
		return gradientPaint
	}

	override fun draw(canvas: Canvas?)
	{
		canvas?.drawRect(0.0f, 0.0f, bounds.width().toFloat(), bounds.height().toFloat(), gradientPaint())
	}

	fun rebuild()
	{
		rebuildGradient = true
		invalidateSelf()
	}

	override fun setAlpha(alpha: Int)
	{
		this.gradientAlpha = alpha
		rebuild()
	}

	fun center(x: Float, y: Float)
	{
		if (type == Type.LINEAR)
		{
			Log.w(this.javaClass.simpleName, "Can't center a linear gradient !")
			return
		}
		centerX = x
		centerY = y
		centerInit = true
		rebuild()
	}

	override fun getOpacity(): Int
	{
		return when (gradientAlpha)
		{
			1 -> PixelFormat.OPAQUE
			0 -> PixelFormat.TRANSPARENT
			else -> PixelFormat.TRANSLUCENT
		}
	}

	override fun setColorFilter(colorFilter: ColorFilter?)
	{
		throw NotImplementedError()
	}
}