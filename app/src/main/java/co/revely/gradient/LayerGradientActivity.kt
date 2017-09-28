package co.revely.gradient

import android.animation.ValueAnimator
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.activity_layer_gradient.*

/**
 * Created at 9/28/17
 *
 * @author rbenjami
 */
class LayerGradientActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_layer_gradient)

		// view_instagram
		RevelyGradient.layer(
				RevelyGradient
						.radial(TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 150f, Resources.getSystem().displayMetrics ))
						.colors(intArrayOf(Color.parseColor("#ffdd55"), Color.parseColor("#ffdd55"), Color.parseColor("#ff543e"), Color.parseColor("#c837ab")))
						.offsets(floatArrayOf(0f, 0.1f, 0.5f, 1f))
						.center(50, 400),
				RevelyGradient
						.radial(TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 170f, Resources.getSystem().displayMetrics ))
						.colors(intArrayOf(Color.parseColor("#3771c8"), Color.parseColor("#3771c8"), Color.parseColor("#006600ff")))
						.offsets(floatArrayOf(0f, 0.128f, 1f))
						.angle(-15f)
						.scale(1f, 0.4f)
						.center(0, 0)
		).onBackgroundOf(view_instagram)

		// view_instagram_animated
		val valueAnimator1 = ValueAnimator.ofFloat(-1f, 1f)
		valueAnimator1.duration = 1000
		valueAnimator1.repeatCount = ValueAnimator.INFINITE
		valueAnimator1.repeatMode = ValueAnimator.REVERSE
		valueAnimator1.interpolator = BounceInterpolator()

		val valueAnimator2 = ValueAnimator.ofFloat(0f, 360f)
		valueAnimator2.duration = 15000
		valueAnimator2.repeatCount = ValueAnimator.INFINITE
		valueAnimator2.interpolator = LinearInterpolator()

		RevelyGradient.layer(
				RevelyGradient
						.radial(TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 150f, Resources.getSystem().displayMetrics ))
						.colors(intArrayOf(Color.parseColor("#ffdd55"), Color.parseColor("#ffdd55"), Color.parseColor("#ff543e"), Color.parseColor("#c837ab")))
						.offsets(floatArrayOf(0f, 0.1f, 0.5f, 1f))
						.center(50, 400)
						.animate(valueAnimator1, { _valueAnimator, _gradientDrawable ->
							val x = (50 + Math.cos((_valueAnimator.animatedValue as Float).toDouble()) * 300).toFloat()
							val y = (200 + Math.sin((_valueAnimator.animatedValue as Float).toDouble()) * 200).toFloat()
							_gradientDrawable.center(x, y)
						} ),
				RevelyGradient
						.radial(TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 170f, Resources.getSystem().displayMetrics ))
						.colors(intArrayOf(Color.parseColor("#3771c8"), Color.parseColor("#3771c8"), Color.parseColor("#006600ff")))
						.offsets(floatArrayOf(0f, 0.128f, 1f))
						.angle(-15f)
						.scale(1f, 0.4f)
						.center(0, 0)
						.animate(valueAnimator2, { _valueAnimator, _gradientDrawable ->
							_gradientDrawable.angle = _valueAnimator.animatedValue as Float
						} )
		).onBackgroundOf(view_instagram_animated)
		valueAnimator1.start()
		valueAnimator2.start()
	}
}