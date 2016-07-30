package com.xiaozhi.firework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.xiaozhi.firework_core.FireWorkView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LinearLayout linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
		//1.新建一个烟花效果(第二个参数是礼物资源Id)
		FireWorkView fireWorkView = new FireWorkView(MainActivity.this, R.mipmap.ic_launcher);
		//2.在布局上增加烟花效果
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
		linearlayout.addView(fireWorkView, layoutParams);
		//3.播放动画
		fireWorkView.playAnim();
	}
}
