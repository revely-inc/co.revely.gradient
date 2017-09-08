package com.revely.gradient

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.revely.gradient.databinding.ActivityTestBinding
import com.revely.gradient.drawables.Gradient


class TestActivity : AppCompatActivity()
{
	private var binding: ActivityTestBinding? = null

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		this.binding = DataBindingUtil.setContentView<ActivityTestBinding>(this, R.layout.activity_test)


		Gradient
				.linear()
				.colors(resources.getIntArray(R.array.colorRevely))
				.on(this.binding!!.testView1)


		val animation = ValueAnimator.ofFloat(0.0f, 1.0f)
		animation.duration = 4000
		animation.repeatMode = ValueAnimator.REVERSE
		animation.repeatCount = ValueAnimator.INFINITE
		//animation.interpolator = OvershootInterpolator()
		Gradient
				.linear()
				.colors(intArrayOf(Color.CYAN, Color.YELLOW, Color.YELLOW, Color.YELLOW))
				.rotate(20.0f)
				.animate(animation, {valueAnimator, gradientDrawable ->
					val v = valueAnimator.animatedValue as Float
					gradientDrawable.colors[0] = ArgbEvaluator().evaluate(v, Color.CYAN, Color.YELLOW) as Int
				})
				.on(this.binding!!.testView2)
		animation.start()


		Gradient
				.linear()
				.colors(resources.getIntArray(R.array.colorRevely))
				.alpha(0.5f)
				.on(this.binding!!.testView3)
	}
}
