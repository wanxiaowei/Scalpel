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
			require.setText("Ҫ��");
			hand.setText("���ͣ�");
			angle.setText("��б�ǣ�");
			force.setText("���ȣ�");
			length.setText("���ȣ�");
			mp.reset();
			now=R.raw.zhigongshi;
			mp = MediaPlayer.create(this, R.raw.zhigongshi);
			mp.setOnCompletionListener(new CompletionListener());
			require.setText("Ҫ�������ִ��ʽִ��");
			mp.start();
			
		}
		}
	}
	class CompletionListener implements OnCompletionListener{

		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			hand.setText("���ͣ�" + Communication.getHand());
			angle.setText("��б�ǣ�" + Communication.getAngle());
			force.setText("���ȣ�" + Communication.getForce());
			switch (now) {
			case R.raw.zhigongshi: {
				if (Communication.getHandid() == 0) {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this, R.raw.qingxie);
					mp.setOnCompletionListener(new CompletionListener());
					now=R.raw.qingxie;
					require.setText("Ҫ���뽫��������б45��");
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
				require.setText("Ҫ�������ִ��ʽִ��");
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
					require.setText("Ҫ���뱣�ֲ����е���ģ���п��������п�����50mm");
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
				require.setText("Ҫ���뽫��������б45��");
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
				require.setText("Ҫ���뱣�ֲ����е���ģ���п��������п�����50mm");
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
						.setText(String.format("����:%.2fcm",
								length / 1366 * 23.44));
			}
		});
	}


}
