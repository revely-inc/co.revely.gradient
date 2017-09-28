package co.revely.gradient

import android.graphics.drawable.LayerDrawable
import android.view.View

/**
 * Created at 9/28/17
 *
 * @author rbenjami
 */
class LayerGradient(var gradients: Array<out RevelyGradient>)
{
	var drawable: LayerDrawable = LayerDrawable(gradients.map { revelyGradient -> revelyGradient.gradient }.toTypedArray())

	fun onBackgroundOf(view: View)
	{
		@Suppress("DEPRECATION")
		view.setBackgroundDrawable(drawable)
	}
}