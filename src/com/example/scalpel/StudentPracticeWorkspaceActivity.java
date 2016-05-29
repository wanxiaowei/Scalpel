package com.example.scalpel;

import java.util.logging.LogRecord;

import com.example.scalpel.MyView.FreshAction;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class StudentPracticeWorkspaceActivity extends Activity implements
		FreshAction {
	TextView require, hand, vetical, angle, force, length, time, total;
	int COL_id, COL_require, COL_hand, COL_angle, COL_force, COL_length,COL_time;
	MyHandler myHandler;
	Thread t;
	boolean screenshot=false;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myworkspace);
		/*
		 * LinearLayout layout = (LinearLayout)
		 * findViewById(R.id.workspace_tool); layout.removeAllViews(); View view
		 * = View .inflate(this, R.layout.myworkspace_item_button, layout);
		 */
		LinearLayout layout = (LinearLayout) findViewById(R.id.workspace_tool);
		layout.removeAllViews();
		View viewtext = View.inflate(this, R.layout.myworkspace_item_practice,
				layout);
		require = (TextView) findViewById(R.id.textview_require);
		hand = (TextView) findViewById(R.id.textview_mark_hand);
		vetical=(TextView) findViewById(R.id.textview_mark_vetical);
		angle = (TextView) findViewById(R.id.textview_mark_angle);
		force = (TextView) findViewById(R.id.textview_mark_force);
		length = (TextView) findViewById(R.id.textview_mark_length);
		time = (TextView) findViewById(R.id.textview_mark_time);
		total = (TextView) findViewById(R.id.textview_mark_total);
		require.setText("要求：");
		hand.setText("手型得分：");
		vetical.setText("垂直度得分：");
		angle.setText("倾斜角得分：");
		force.setText("行刀力量得分：");
		length.setText("行刀距离得分：");
		time.setText("行刀时间得分：");
		total.setText("总得分：");
		ScalpelDB scalpeldb = new ScalpelDB(this);
		int tmp = getIntent().getIntExtra("problem_id", 0);
		SQLiteDatabase db = scalpeldb.getReadableDatabase();
		String sql = "SELECT * FROM problem WHERE id = \"" + tmp + "\"";
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		COL_id = cursor.getColumnIndex("id");
		COL_require = cursor.getColumnIndex("require");
		COL_hand = cursor.getColumnIndex("hand");
		COL_angle = cursor.getColumnIndex("angle");
		COL_force = cursor.getColumnIndex("force");
		COL_length = cursor.getColumnIndex("length");
		COL_time = cursor.getColumnIndex("time");
		String RE = cursor.getString(COL_require);
		Communication.setStdHand(cursor.getInt(COL_hand));
		Communication.setStdAngle(cursor.getInt(COL_angle));
		Communication.setStdForce(cursor.getInt(COL_force));
		Communication.setStdLength(cursor.getInt(COL_length));
		Communication.setStdTime(cursor.getInt(COL_time));
		require.setText(RE);
		scalpeldb.close();
		Communication.ReceiveStart();
		myHandler = new MyHandler();
		myThread m = new myThread();
		t=new Thread(m);
		t.start();
	}

	class MyHandler extends Handler {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 8100: {
				hand.setText("手型得分：" + Communication.marhand);
				vetical.setText("垂直度得分："+Communication.marvetical);
				angle.setText("倾斜角得分：" + Communication.marangle);
				force.setText("行刀力量得分：" + Communication.marforce);
				length.setText("行刀距离得分：" + Communication.marleng);
				time.setText("行刀时间得分："+Communication.martime);
				total.setText("总得分：" + Communication.martotal);
				screenshot=true;
				break;
			}
			}
			super.handleMessage(msg);
		}
	}

	class myThread implements Runnable {
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {

				Message message = new Message();
				message.what = 8100;
				if (Communication.getReceive() == false) {
					StudentPracticeWorkspaceActivity.this.myHandler
							.sendMessage(message);
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_back: {
			onBackPressed();
			break;
		}
		case R.id.button_screenshot:{
			if(screenshot==false){
				Toast.makeText(StudentPracticeWorkspaceActivity.this, "请先完成题目", Toast.LENGTH_SHORT).show();
			}
			else{
				if(ScreenShotUtils.shotBitmap(StudentPracticeWorkspaceActivity.this)){
					Toast.makeText(StudentPracticeWorkspaceActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
				}
				else{
					Toast.makeText(StudentPracticeWorkspaceActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
				}
				screenshot=false;
			}
			break;
		}
		}
	}

	@Override
	public void freshAction(double length) {
		// TODO Auto-generated method stub

	}
}
