package com.example.scalpel;

import java.io.IOException;

import com.example.scalpel.MyView.FreshAction;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class StudentGuideActivity extends Activity implements FreshAction {
	MediaPlayer mp = new MediaPlayer();
	int now;
	float nowlength;
	boolean lengthflag=false;
	TextView require,hand,angle,force,length;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myworkspace);
		LinearLayout layout = (LinearLayout) findViewById(R.id.workspace_tool);
		layout.removeAllViews();
		View view = View
				.inflate(this, R.layout.myworkspace_item_button, layout);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_back: {
			mp.release();
			onBackPressed();
			break;
		}
		case R.id.button_zhigong: {
			LinearLayout layout = (LinearLayout) findViewById(R.id.workspace_tool);
			layout.removeAllViews();
			View viewtext = View.inflate(this, R.layout.myworkspace_item_text,
					layout);
			require = (TextView) findViewById(R.id.textview_require);
			hand = (TextView) findViewById(R.id.textview_status_hand);
			angle = (TextView) findViewById(R.id.textview_status_angle);
			force = (TextView) findViewById(R.id.textview_status_force);
			length = (TextView) findViewById(R.id.textview_status_length);
			require.setText("要求：");
			hand.setText("手型：");
			angle.setText("倾斜角：");
			force.setText("力度：");
			length.setText("长度：");
			mp.reset();
			now=R.raw.zhigongshi;
			mp = MediaPlayer.create(this, R.raw.zhigongshi);
			mp.setOnCompletionListener(new CompletionListener());
			require.setText("要求：请采用执弓式执刀");
			mp.start();
			
		}
		}
	}
	class CompletionListener implements OnCompletionListener{

		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			hand.setText("手型：" + Communication.getHand());
			angle.setText("倾斜角：" + Communication.getAngle());
			force.setText("力度：" + Communication.getForce());
			switch (now) {
			case R.raw.zhigongshi: {
				if (Communication.getHandid() == 0) {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.qingxie);
					mp.setOnCompletionListener(new CompletionListener());
					now=R.raw.qingxie;
					require.setText("要求：请将手术刀倾斜45°");
					mp.start();
				}
				else {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.shouxingwrong);
					mp.setOnCompletionListener(new CompletionListener());
					now=R.raw.shouxingwrong;
					mp.start();
				}
				break;
			}
			case R.raw.shouxingwrong:{
				mp.reset();
				mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.zhigongshi);
				mp.setOnCompletionListener(new CompletionListener());
				now=R.raw.zhigongshi;
				require.setText("要求：请采用执弓式执刀");
				mp.start();
				break;
			}
			case R.raw.qingxie:{
				int jd=Communication.getAngle();
				if(jd>=40&&jd<=50){
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.changdu);
					mp.setOnCompletionListener(new CompletionListener());
					now=R.raw.changdu;
					require.setText("要求：请保持并在行刀区模拟切开操作，切开长度50mm");
					Communication.setLeng(0);
					mp.start();
				}
				else {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.qingxiewrong);
					mp.setOnCompletionListener(new CompletionListener());
					now=R.raw.qingxiewrong;
					mp.start();
				}
				break;
			}
			case R.raw.qingxiewrong:{
				mp.reset();
				mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.qingxie);
				mp.setOnCompletionListener(new CompletionListener());
				now=R.raw.qingxie;
				require.setText("要求：请将手术刀倾斜45°");
				mp.start();
				break;
			}
			case R.raw.changdu:{
				double tmp=Communication.getLeng();
				if(tmp>4&&tmp<6){
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.correct);
					mp.setOnCompletionListener(new CompletionListener());
					now=R.raw.correct;
					mp.start();
				}
				else{
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.changduwrong);
					mp.setOnCompletionListener(new CompletionListener());
					now=R.raw.changduwrong;
					mp.start();
				}
				break;
			}
			case R.raw.changduwrong:{
				mp.reset();
				mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.changdu);
				mp.setOnCompletionListener(new CompletionListener());
				now=R.raw.changdu;
				require.setText("要求：请保持并在行刀区模拟切开操作，切开长度50mm");
				Communication.setLeng(0);
				mp.start();
				break;
			}
			case R.raw.correct:{
				mp.release();
				break;
			}
			}
		}
		
	}
	@Override
	public void freshAction(final double length) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				((TextView) findViewById(R.id.textview_status_length))
						.setText(String.format("长度:%.2fcm",
								length / 1366 * 23.44));
			}
		});
	}


}
