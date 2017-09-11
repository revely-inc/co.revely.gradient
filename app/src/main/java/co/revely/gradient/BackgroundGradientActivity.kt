package co.revely.gradient

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_background_gradient.*

class BackgroundGradientActivity : AppCompatActivity()
{
	private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

	object Data
	{
		class Gradient(var angle: Float, var colors: List<String>)

		val GRADIENT_DATAS: List<Gradient> = mutableListOf(
				Gradient(0f, mutableListOf("#17EA19", "#6078EA")),
				Gradient(-19f, mutableListOf("#21D4FD", "#B721FF")),
				Gradient(90f, mutableListOf("#FEE140", "#FA709A")),
				Gradient(-45f, mutableListOf("#FA8BFF", "#2BD2FF", "#2BFF88")),
				Gradient(-43f, mutableListOf("#4158D0", "#C850C0", "#FFCC70"))
		)
	}

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_background_gradient)

		mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

		container.adapter = mSectionsPagerAdapter
	}


	inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm)
	{
		override fun getItem(position: Int): Fragment
		{
			return BackgroundGradientFragment.newInstance(position)
		}

		override fun getCount(): Int
		{
			return BackgroundGradientActivity.Data.GRADIENT_DATAS.size
		}
	}


	class BackgroundGradientFragment : Fragment()
	{
		override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
		{
			val rootView = inflater.inflate(R.layout.fragment_background_gradient, container, false)
			val gradientData: Data.Gradient = BackgroundGradientActivity.Data.GRADIENT_DATAS[arguments.getInt(ARG_POSITION)]

			RevelyGradient.linear()
					.angle(gradientData.angle)
					.colors(gradientData.colors.map { s -> Color.parseColor(s) }.toIntArray())
					.onBackgroundOf(rootView.findViewById<View>(R.id.constraintLayout))

			val colorsString = SpannableStringBuilder()
			for ( color in gradientData.colors )
			{
				colorsString.append(color)
				colorsString.setSpan(ForegroundColorSpan(Color.parseColor(color)), colorsString.length - color.length, colorsString.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
				colorsString.append(" ")
			}

			rootView.findViewById<TextView>(R.id.colors).text = colorsString
			return rootView
		}

		companion object
		{
			private val ARG_POSITION = "position"

			fun newInstance(position: Int): BackgroundGradientFragment
			{
				val fragment = BackgroundGradientFragment()
				val args = Bundle()
				args.putInt(ARG_POSITION, position)
				fragment.arguments = args
				return fragment
			}
		}
	}
}
