package co.revely.gradient

import android.animation.ValueAnimator
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import co.revely.gradient.drawables.Gradient

/**
 * Created at 08/09/17
 *
 * @author rbenjami
 */
class RevelyGradient(type: Gradient.Type)
{
	private var gradient: Gradient = Gradient(type)

	private lateinit var applyGradient: (() -> Unit)
	private var onUpdate: ((valueAnimator: ValueAnimator, gradient: Gradient) -> Unit)? = null

	companion object
	{
		fun linear(): RevelyGradient = RevelyGradient(Gradient.Type.LINEAR)
		fun radial(): RevelyGradient = RevelyGradient(Gradient.Type.RADIAL)
		fun sweep(): RevelyGradient = RevelyGradient(Gradient.Type.SWEEP)
	}

	fun on(view: TextView)
	{
		view.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> applyGradient() }
		applyGradient = {
			gradient.getPaint(view.width, view.height, true)
			view.paint.shader = gradient.shader
			view.invalidate()
		}
	}

	fun on(view: ImageView)
	{
		applyGradient = { view.setImageDrawable(gradient) }
		applyGradient()
	}

	fun on(applyGradient: ((gradient: Gradient) -> Unit))
	{
		this.applyGradient = { applyGradient(gradient) }
		this.applyGradient()
	}

	fun onBackgroundOf(view: View)
	{
		applyGradient = {
			@Suppress("DEPRECATION")
			view.setBackgroundDrawable(gradient)
		}
		applyGradient()
	}

	fun colors(colors: IntArray): RevelyGradient
	{
		gradient.colors = colors
		return this
	}

	fun alpha(alpha: Float): RevelyGradient
	{
		gradient.alpha = (alpha * 255).toInt()
		return this
	}

	fun center(x: Int, y: Int): RevelyGradient
	{
		gradient.center(x.toFloat(), y.toFloat())
		return this
	}

	fun angle(angle: Float): RevelyGradient
	{
		gradient.angle = angle
		return this
	}

	fun animate(valueAnimator: ValueAnimator, onUpdate: (valueAnimator: ValueAnimator, gradient: Gradient) -> Unit): RevelyGradient
	{
		this.onUpdate = onUpdate
		valueAnimator.addUpdateListener { _valueAnimator ->
			this.onUpdate!!(_valueAnimator, gradient)
			gradient.rebuild()
			applyGradient()
		}
		return this
	}
}