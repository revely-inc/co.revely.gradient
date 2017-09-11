package co.revely.gradient

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_background_gradient.*

class BackgroundGradientActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_background_gradient)


		RevelyGradient
				.linear()
				.colors(resources.getIntArray(R.array.colorRevely))
				.onBackgroundOf(test_view1)


		RevelyGradient
				.linear()
				.angle(90.0f)
				.colors(intArrayOf(Color.CYAN, Color.YELLOW))
				.onBackgroundOf(test_view2)


		RevelyGradient
				.linear()
				.colors(resources.getIntArray(R.array.colorRevely))
				.angle(-50.0f)
				.alpha(0.5f)
				.onBackgroundOf(test_view3)
	}
}
