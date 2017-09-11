package co.revely.gradient

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_animated_gradient.*


class AnimatedGradientActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_animated_gradient)

		val color1 = Color.parseColor("#00c6ff")
		val color2 = Color.parseColor("#0072ff")

		val valueAnimator = ValueAnimator.ofFloat(0f, 360f)
		valueAnimator.duration = 15000
		valueAnimator.repeatCount = ValueAnimator.INFINITE
		valueAnimator.interpolator = LinearInterpolator()
		RevelyGradient.sweep()
				.colors(intArrayOf(color1, color2, color1))
				.center(540, 400)
				.animate(valueAnimator, { _valueAnimator, _gradientDrawable ->
					_gradientDrawable.angle = _valueAnimator.animatedValue as Float
				})
				.onBackgroundOf(container)
		valueAnimator.start()
	}
}
