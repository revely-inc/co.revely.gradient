package co.revely.gradient;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created at 9/12/17
 *
 * @author rbenjami
 */
public class JavaGradientActivity extends AppCompatActivity
{
	@Override
	public void onCreate(@Nullable Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_java_gradient);

		RevelyGradient
				.linear()
				.colors(new int[] {Color.parseColor("#FF2525"), Color.parseColor("#6078EA")})
				.onBackgroundOf(findViewById(R.id.container));

		RevelyGradient
				.linear()
				.colors(new int[] {Color.parseColor("#AA000000"), Color.parseColor("#00000000")})
				.angle(90)
				.onBackgroundOf(findViewById(R.id.view));
	}
}
