package co.revely.gradient

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_text_gradient.*

class TextGradientActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_text_gradient)
		setSupportActionBar(toolbar)

		RevelyGradient.linear()
				.colors(resources.getIntArray(R.array.colorTest2))
				.angle(40.0f)
				.onBackgroundOf(app_bar)


		RevelyGradient.linear()
				.colors(intArrayOf(Color.parseColor("#FFE78E"), Color.parseColor("#F28381")))
				.on(title_text)

		RevelyGradient.linear()
				.angle(90f)
				.colors(intArrayOf(Color.parseColor("#00FF83"), Color.parseColor("#69FFFE")))
				.on(title_text2)
	}
}
