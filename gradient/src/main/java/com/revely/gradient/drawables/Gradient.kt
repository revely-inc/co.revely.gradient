package com.revely.gradient.drawables

import android.animation.ValueAnimator
import android.view.View

/**
 * Created at 08/09/17
 *
 * @author rbenjami
 */
abstract class Gradient
{
	protected var gradientDrawable: GradientDrawable = GradientDrawable(GradientDrawable.Type.LINEAR)

	private var onUpdate: ((valueAnimator: ValueAnimator, gradientDrawable: GradientDrawable) -> Unit)? = null

	class Linear : Gradient()
	{
		init
		{
			gradientDrawable.type = GradientDrawable.Type.LINEAR
		}
	}

	class Radial : Gradient()
	{
		init
		{
			gradientDrawable.type = GradientDrawable.Type.RADIAL
		}
	}

	class Sweep : Gradient()
	{
		init
		{
			gradientDrawable.type = GradientDrawable.Type.SWEEP
		}
	}

	companion object
	{
		fun linear(): Linear = Linear()
		fun radial(): Radial = Radial()
		fun sweep(): Sweep = Sweep()
	}

	fun on(view: View)
	{
		@Suppress("DEPRECATION")
		view.setBackgroundDrawable(gradientDrawable)
	}

	fun colors(colors: IntArray): Gradient
	{
		gradientDrawable.colors = colors
		return this
	}

	fun alpha(alpha: Float): Gradient
	{
		gradientDrawable.alpha = (alpha * 255).toInt()
		return this
	}

	fun center(x: Int, y: Int): Gradient
	{
		gradientDrawable.center(x.toFloat(), y.toFloat())
		return this
	}

	fun rotate(rotation: Float): Gradient
	{
		gradientDrawable.rotation = rotation
		return this
	}

	fun animate(valueAnimator: ValueAnimator, onUpdate: (valueAnimator: ValueAnimator, gradientDrawable: GradientDrawable) -> Unit): Gradient
	{
		this.onUpdate = onUpdate
		valueAnimator.addUpdateListener { _valueAnimator ->
			this.onUpdate!!(_valueAnimator, gradientDrawable)
			gradientDrawable.rebuild()
		}
		return this
	}
}