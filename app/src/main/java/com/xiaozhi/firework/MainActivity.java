package com.xiaozhi.firework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private FireWorkView fireWorkView;
	private LinearLayout linearlayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
	}

	public void addFlowerView(View view) {
		if(fireWorkView == null){
			fireWorkView = new FireWorkView(this, R.drawable.rose);
			addFireView();
		}
	}

	public void addHeartView(View view) {
		if(fireWorkView == null){
			fireWorkView = new FireWorkView(this, R.drawable.heart);
			addFireView();
		}
	}

	private void addFireView() {
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		linearlayout.addView(fireWorkView, layoutParams);
		fireWorkView.playAnim();
	}

	public void removeAnimal(View view) {
		linearlayout.removeView(fireWorkView);
		fireWorkView.stopAnim();
		fireWorkView = null;
	}

}
