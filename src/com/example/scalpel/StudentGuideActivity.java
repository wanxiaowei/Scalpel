package com.example.scalpel;

import java.io.IOException;
import java.text.DecimalFormat;

import com.example.scalpel.MyView.FreshAction;

import android.app.Activity;
import android.graphics.Color;
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
	boolean lengthflag = false;
	boolean fangle, fvetical, fforce, flength, ftime;
	TextView require, hand, angle, force, length, vetical, time;
	int handid;
	DecimalFormat df = new DecimalFormat("0.00");

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
			vetical = (TextView) findViewById(R.id.textview_status_vetical);
			force = (TextView) findViewById(R.id.textview_status_force);
			length = (TextView) findViewById(R.id.textview_status_length);
			time = (TextView) findViewById(R.id.textview_status_time);
			require.setText("要求：");
			hand.setText("手型：");
			vetical.setText("垂直度：");
			angle.setText("倾斜角：");
			force.setText("行刀力量：");
			length.setText("行刀距离：");
			time.setText("行刀时间：");
			mp.reset();
			now = R.raw.zhigongshi;
			mp = MediaPlayer.create(this, R.raw.zhigongshi);
			mp.setOnCompletionListener(new CompletionListener());
			require.setText("要求：请采用执弓式手型执刀");
			handid = 1;
			mp.start();
			break;
		}
		case R.id.button_wochi: {
			LinearLayout layout = (LinearLayout) findViewById(R.id.workspace_tool);
			layout.removeAllViews();
			View viewtext = View.inflate(this, R.layout.myworkspace_item_text,
					layout);
			require = (TextView) findViewById(R.id.textview_require);
			hand = (TextView) findViewById(R.id.textview_status_hand);
			angle = (TextView) findViewById(R.id.textview_status_angle);
			vetical = (TextView) findViewById(R.id.textview_status_vetical);
			force = (TextView) findViewById(R.id.textview_status_force);
			length = (TextView) findViewById(R.id.textview_status_length);
			time = (TextView) findViewById(R.id.textview_status_time);
			require.setText("要求：");
			hand.setText("手型：");
			vetical.setText("垂直度：");
			angle.setText("倾斜角：");
			force.setText("行刀力量：");
			length.setText("行刀距离：");
			time.setText("行刀时间：");
			mp.reset();
			now = R.raw.wochishi;
			mp = MediaPlayer.create(this, R.raw.wochishi);
			mp.setOnCompletionListener(new CompletionListener());
			require.setText("要求：请采用握持式手型执刀");
			handid = 2;
			mp.start();
			break;
		}
		case R.id.button_zhibi: {
			LinearLayout layout = (LinearLayout) findViewById(R.id.workspace_tool);
			layout.removeAllViews();
			View viewtext = View.inflate(this, R.layout.myworkspace_item_text,
					layout);
			require = (TextView) findViewById(R.id.textview_require);
			hand = (TextView) findViewById(R.id.textview_status_hand);
			angle = (TextView) findViewById(R.id.textview_status_angle);
			vetical = (TextView) findViewById(R.id.textview_status_vetical);
			force = (TextView) findViewById(R.id.textview_status_force);
			length = (TextView) findViewById(R.id.textview_status_length);
			time = (TextView) findViewById(R.id.textview_status_time);
			require.setText("要求：");
			hand.setText("手型：");
			vetical.setText("垂直度：");
			angle.setText("倾斜角：");
			force.setText("行刀力量：");
			length.setText("行刀距离：");
			time.setText("行刀时间：");
			mp.reset();
			now = R.raw.zhibishi;
			mp = MediaPlayer.create(this, R.raw.zhibishi);
			mp.setOnCompletionListener(new CompletionListener());
			require.setText("要求：请采用执笔式手型执刀");
			handid = 3;
			mp.start();
			break;
		}
		case R.id.button_fantiao: {
			LinearLayout layout = (LinearLayout) findViewById(R.id.workspace_tool);
			layout.removeAllViews();
			View viewtext = View.inflate(this, R.layout.myworkspace_item_text,
					layout);
			require = (TextView) findViewById(R.id.textview_require);
			hand = (TextView) findViewById(R.id.textview_status_hand);
			angle = (TextView) findViewById(R.id.textview_status_angle);
			vetical = (TextView) findViewById(R.id.textview_status_vetical);
			force = (TextView) findViewById(R.id.textview_status_force);
			length = (TextView) findViewById(R.id.textview_status_length);
			time = (TextView) findViewById(R.id.textview_status_time);
			require.setText("要求：");
			hand.setText("手型：");
			vetical.setText("垂直度：");
			angle.setText("倾斜角：");
			force.setText("行刀力量：");
			length.setText("行刀距离：");
			time.setText("行刀时间：");
			mp.reset();
			now = R.raw.fantiaoshi;
			mp = MediaPlayer.create(this, R.raw.fantiaoshi);
			mp.setOnCompletionListener(new CompletionListener());
			require.setText("要求：请采用反挑式手型执刀");
			handid = 4;
			mp.start();
			break;
		}
		}
	}

	class CompletionListener implements OnCompletionListener {

		@Override
		public void onCompletion(MediaPlayer arg0) {
			// TODO Auto-generated method stub
			switch (now) {
			case R.raw.zhigongshi: {
				hand.setText("手型：" + Communication.getHand());
				if (Communication.getHandid() == 1) {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.qingxie);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.qingxie;
					require.setText("要求：请保持并请将手术刀倾斜45°，保持刀面与操作面垂直");
					hand.setBackgroundColor(Color.GREEN);
					mp.start();
				} else {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.shouxingwrong);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.shouxingwrong;
					hand.setBackgroundColor(Color.RED);
					mp.start();
				}
				break;
			}
			case R.raw.wochishi: {
				hand.setText("手型：" + Communication.getHand());
				if (Communication.getHandid() == 2) {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.qingxie);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.qingxie;
					require.setText("要求：请保持并请将手术刀倾斜45°，保持刀面与操作面垂直");
					hand.setBackgroundColor(Color.GREEN);
					mp.start();
				} else {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.shouxingwrong);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.shouxingwrong;
					hand.setBackgroundColor(Color.RED);
					mp.start();
				}
				break;
			}
			case R.raw.zhibishi: {
				hand.setText("手型：" + Communication.getHand());
				if (Communication.getHandid() == 3) {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.qingxie);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.qingxie;
					require.setText("要求：请保持并请将手术刀倾斜45°，保持刀面与操作面垂直");
					hand.setBackgroundColor(Color.GREEN);
					mp.start();
				} else {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.shouxingwrong);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.shouxingwrong;
					hand.setBackgroundColor(Color.RED);
					mp.start();
				}
				break;
			}
			case R.raw.fantiaoshi: {
				hand.setText("手型：" + Communication.getHand());
				if (Communication.getHandid() == 4) {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.qingxie);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.qingxie;
					require.setText("要求：请保持并请将手术刀倾斜45°，保持刀面与操作面垂直");
					hand.setBackgroundColor(Color.GREEN);
					mp.start();
				} else {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.shouxingwrong);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.shouxingwrong;
					hand.setBackgroundColor(Color.RED);
					mp.start();
				}
				break;
			}
			case R.raw.shouxingwrong: {
				mp.reset();
				if (handid == 1) {
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.zhigongshi);
					now = R.raw.zhigongshi;
				} else if (handid == 2) {
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.wochishi);
					now = R.raw.wochishi;
				} else if (handid == 3) {
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.zhibishi);
					now = R.raw.zhibishi;
				} else {
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.fantiaoshi);
					now = R.raw.fantiaoshi;
				}
				mp.setOnCompletionListener(new CompletionListener());
				mp.start();
				break;
			}
			case R.raw.qingxie: {
				angle.setText("倾斜角：" + Communication.getAngle() + "°");
				vetical.setText("垂直度：" + Communication.getVetical() + "°");
				int jda = Communication.getAngle();
				int jdv = Communication.getVetical();
				if (jda >= 40 && jda <= 50)
					fangle = true;
				else
					fangle = false;
				if (jdv >= 80 && jdv <= 100)
					fvetical = true;
				else
					fvetical = false;
				if (fangle && fvetical) {
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.changdu);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.changdu;
					require.setText("要求：请保持并在行刀区模拟切开操作，行刀力量100g，行刀距离5cm，行刀时间5s");
					angle.setBackgroundColor(Color.GREEN);
					vetical.setBackgroundColor(Color.GREEN);
					Communication.setLeng(0);
					mp.start();
				} else {
					mp.reset();
					if (!fangle) {
						mp = MediaPlayer.create(StudentGuideActivity.this,
								R.raw.qingxiewrong);
						now = R.raw.qingxiewrong;
						angle.setBackgroundColor(Color.RED);
					} else {
						angle.setBackgroundColor(Color.GREEN);
						mp = MediaPlayer.create(StudentGuideActivity.this,
								R.raw.chuizhiwrong);
						now = R.raw.chuizhiwrong;
						vetical.setBackgroundColor(Color.RED);
					}
					mp.setOnCompletionListener(new CompletionListener());
					mp.start();
				}
				break;
			}
			case R.raw.qingxiewrong: {
				mp.reset();
				mp = MediaPlayer.create(StudentGuideActivity.this,
						R.raw.qingxie);
				mp.setOnCompletionListener(new CompletionListener());
				now = R.raw.qingxie;
				mp.start();
				break;
			}
			case R.raw.chuizhiwrong: {
				mp.reset();
				mp = MediaPlayer.create(StudentGuideActivity.this,
						R.raw.qingxie);
				mp.setOnCompletionListener(new CompletionListener());
				now = R.raw.qingxie;
				mp.start();
				break;
			}
			case R.raw.changdu: {
				double cdl = Communication.getLeng();
				int cdf = Communication.getForce();
				double cdt = Communication.getTime();
				force.setText("行刀力量：" + cdf + "g");
				time.setText("行刀时间：" + df.format(cdt) + "s");
				if (cdf > 80 && cdf < 120)
					fforce = true;
				else
					fforce = false;
				if (cdl > 4 && cdl < 6)
					flength = true;
				else
					flength = false;
				if (cdt > 4 && cdt < 6)
					ftime = true;
				else
					ftime = false;
				if (fforce && flength && ftime) {
					force.setBackgroundColor(Color.GREEN);
					length.setBackgroundColor(Color.GREEN);
					time.setBackgroundColor(Color.GREEN);
					mp.reset();
					mp = MediaPlayer.create(StudentGuideActivity.this,
							R.raw.correct);
					mp.setOnCompletionListener(new CompletionListener());
					now = R.raw.correct;
					length.setBackgroundColor(Color.GREEN);
					mp.start();
				} else {
					mp.reset();
					if (!fforce) {
						mp = MediaPlayer.create(StudentGuideActivity.this,
								R.raw.liduwrong);
						now = R.raw.liduwrong;
						force.setBackgroundColor(Color.RED);
					} else if (!flength) {
						force.setBackgroundColor(Color.GREEN);
						mp = MediaPlayer.create(StudentGuideActivity.this,
								R.raw.changduwrong);
						now = R.raw.changduwrong;
						length.setBackgroundColor(Color.RED);
					} else {
						force.setBackgroundColor(Color.GREEN);
						length.setBackgroundColor(Color.GREEN);
						mp = MediaPlayer.create(StudentGuideActivity.this,
								R.raw.timewrong);
						now = R.raw.timewrong;
						time.setBackgroundColor(Color.RED);
					}
					mp.setOnCompletionListener(new CompletionListener());
					mp.start();
				}
				break;
			}
			case R.raw.changduwrong: {
				mp.reset();
				mp = MediaPlayer.create(StudentGuideActivity.this,
						R.raw.changdu);
				mp.setOnCompletionListener(new CompletionListener());
				now = R.raw.changdu;
				Communication.setLeng(0);
				mp.start();
				break;
			}
			case R.raw.liduwrong: {
				mp.reset();
				mp = MediaPlayer.create(StudentGuideActivity.this,
						R.raw.changdu);
				mp.setOnCompletionListener(new CompletionListener());
				now = R.raw.changdu;
				Communication.setLeng(0);
				mp.start();
				break;
			}
			case R.raw.timewrong: {
				mp.reset();
				mp = MediaPlayer.create(StudentGuideActivity.this,
						R.raw.changdu);
				mp.setOnCompletionListener(new CompletionListener());
				now = R.raw.changdu;
				Communication.setLeng(0);
				mp.start();
				break;
			}
			case R.raw.correct: {
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
						.setText(String.format("长度：%.2fcm",
								length / 1366 * 23.44));
			}
		});
	}

}
