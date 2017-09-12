package co.revely.gradient

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_other_gradient.*

/**
 * Created at 9/11/17
 *
 * @author rbenjami
 */
class OtherGradientActivity : AppCompatActivity()
{
	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_other_gradient)

		RevelyGradient
				.linear()
				.colors(intArrayOf(Color.parseColor("#FF2525"), Color.parseColor("#6078EA")))
				.alpha(0.3f)
				.on(gradient1)

		RevelyGradient
				.radial()
				.colors(intArrayOf(Color.parseColor("#FFFFFF"), Color.parseColor("#FAACA8")))
				.alpha(0.3f)
				.angle(-45f)
				.on(gradient2)

		RevelyGradient
				.linear()
				.colors(intArrayOf(Color.parseColor("#000000"), Color.parseColor("#00FFFFFF")))
				.angle(-90f)
				.on(gradient3)
	}
}