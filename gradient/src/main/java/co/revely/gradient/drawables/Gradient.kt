package co.revely.gradient.drawables

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Log

/**
 * Created at 08/09/17
 *
 * @author rbenjami
 */
class Gradient(
		private var type: Type = Gradient.Type.LINEAR,
		colors: IntArray = intArrayOf(Color.WHITE, Color.BLACK),
		angle: Float = 0f,
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

	var colors: IntArray = colors
		set(colors)
		{
			field = colors
			rebuild()
		}
	var angle: Float = angle
		set(angle)
		{
			if (type == Type.RADIAL)
			{
				Log.w(this.javaClass.simpleName, "Angle is useless on radial gradient !")
				return
			}
			field = angle
			rebuild()
		}
	var tileMode: Shader.TileMode = tileMode
		set(tileMode)
		{
			field = tileMode
			rebuild()
		}
	var shader: Shader? = null

	fun getPaint(width: Int, height: Int, forceRebuild: Boolean = false): Paint?
	{
		if (rebuildGradient || forceRebuild)
		{
			if (!centerInit)
			{
				centerX = width / 2.0f
				centerY = height / 2.0f
				centerInit = true
			}

			rotateMatrix.setRotate(angle, centerX, centerY)
			shader = when (type)
			{
				Type.LINEAR -> {
					val angleInRadian = Math.toRadians(angle.toDouble())
					val w = Math.cos(angleInRadian).toFloat() * width / 2
					val h = Math.sin(angleInRadian).toFloat() * height / 2
					LinearGradient(centerX - w, centerY - h, centerX + w, centerY + h, colors, offsets, tileMode)
				}
				Type.RADIAL -> {
					val radius = Math.max(width, height) / 2f
					val shader = RadialGradient(centerX, centerY, radius, colors, offsets, tileMode)
					shader.setLocalMatrix(rotateMatrix)
					shader
				}
				Type.SWEEP -> {
					val shader = SweepGradient(centerX, centerY, colors, offsets)
					shader.setLocalMatrix(rotateMatrix)
					shader
				}
			}

			gradientPaint.reset()
			gradientPaint.shader = shader
			gradientPaint.alpha = gradientAlpha
			rebuildGradient = false
			return gradientPaint
		}
		return gradientPaint
	}

	override fun draw(canvas: Canvas?)
	{
		canvas?.drawRect(0f, 0f, bounds.width().toFloat(), bounds.height().toFloat(), getPaint(bounds.width(), bounds.height()))
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