package com.example.scalpel;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.example.scalpel.MyView.FreshAction;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StudentHomeworkActivity extends Activity implements FreshAction {
	TextView require;
	int COL_id, COL_require, COL_hand, COL_angle, COL_force, COL_length,
			COL_pro_id;
	MyHandler myHandler;
	Thread t;
	Cursor cursor, cursor_contest;
	int nowmsg;
	ScalpelDB scalpeldb;
	double martotal, marhand, marvetical, marangle, marforce, marleng, martime;
	boolean screenshot = false;
	ArrayList<mark> mk = new ArrayList();
	String name;

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
		View viewtext = View.inflate(this, R.layout.myworkspace_item_homework,
				layout);
		require = (TextView) findViewById(R.id.textview_require);
		require.setText("要求：");
		scalpeldb = new ScalpelDB(this);
		name = getIntent().getStringExtra("name");
		marhand = 0;
		marangle = 0;
		marforce = 0;
		marleng = 0;
		martotal = 0;
		marvetical = 0;
		martime = 0;
		SQLiteDatabase db = scalpeldb.getReadableDatabase();
		String sql = "SELECT * FROM homework";
		cursor_contest = db.rawQuery(sql, null);
		if(cursor_contest.getCount()==0){
			Toast.makeText(StudentHomeworkActivity.this, "当前无作业", Toast.LENGTH_SHORT).show();
			onBackPressed();
			return;
		}
		nowmsg = 1;
		cursor_contest.moveToFirst();
		COL_pro_id = cursor_contest.getColumnIndex("problem_id");
		sql = "SELECT * FROM problem WHERE id = \""
				+ cursor_contest.getInt(COL_pro_id) + "\"";
		cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		COL_id = cursor.getColumnIndex("id");
		COL_require = cursor.getColumnIndex("require");
		COL_hand = cursor.getColumnIndex("hand");
		COL_angle = cursor.getColumnIndex("angle");
		COL_force = cursor.getColumnIndex("force");
		COL_length = cursor.getColumnIndex("length");
		String RE = cursor.getString(COL_require);
		Communication.setStdHand(cursor.getInt(COL_hand));
		Communication.setStdAngle(cursor.getInt(COL_angle));
		Communication.setStdForce(cursor.getInt(COL_force));
		Communication.setStdLength(cursor.getInt(COL_length));
		require.setText(RE + 1);
		Communication.ReceiveStart();
		myHandler = new MyHandler();
		myThread m = new myThread();
		t = new Thread(m);
		t.start();
	}

	class MyHandler extends Handler {
		public void handleMessage(Message msg) {
			int tmp = msg.what;
			if (tmp == 5) {
				martotal += Communication.martotal / 5;
				marhand += Communication.marhand / 5;
				marvetical += Communication.marvetical / 5;
				marangle += Communication.marangle / 5;
				marleng += Communication.marleng / 5;
				marforce += Communication.marforce / 5;
				martime += Communication.martime / 5;
				int tid = cursor_contest.getInt(COL_pro_id);
				mk.add(new mark(tid, marhand, marvetical, marangle, marforce,
						marleng, martime, martotal));
				if (cursor_contest.moveToNext()) {
					SQLiteDatabase db = scalpeldb.getReadableDatabase();
					String sql = "SELECT * FROM problem WHERE id = \""
							+ cursor_contest.getInt(COL_pro_id) + "\"";
					cursor = db.rawQuery(sql, null);
					cursor.moveToFirst();
					String RE = cursor.getString(COL_require);
					Communication.setStdHand(cursor.getInt(COL_hand));
					Communication.setStdAngle(cursor.getInt(COL_angle));
					Communication.setStdForce(cursor.getInt(COL_force));
					Communication.setStdLength(cursor.getInt(COL_length));
					require.setText(RE + 1);
					marhand = 0;
					marangle = 0;
					marforce = 0;
					marleng = 0;
					martotal = 0;
					marvetical = 0;
					martime = 0;
					Communication.ReceiveStart();
					nowmsg = 1;
					myThread m = new myThread();
					t = new Thread(m);
					t.start();
					db.close();
				} else {
					SQLiteDatabase db = scalpeldb.getReadableDatabase();
					TextView tv = new TextView(StudentHomeworkActivity.this);
					int n = mk.size();
					String words = "";
					String res = "INSERT INTO result (name";
					for (char i = 'A'; i <= 'A' + n - 1; i++)
						res = res + ",pro" + i;
					res = res + ") VALUES ('" + name + "'";
					for (int i = 0; i < n; i++) {
						mark now = mk.get(i);
						String sql = "SELECT * FROM problem WHERE id=\""
								+ now.id + "\"";
						Cursor cjd = db.rawQuery(sql, null);
						cjd.moveToFirst();
						words = words + cjd.getString(COL_require) + "\n";
						words = words + now.mytoString() + "\n";
						res = res + ",'" + now.mytoString() + "'";
					}
					SQLiteDatabase dbwr = scalpeldb.getWritableDatabase();
					res = res + ")";
					System.out.println(res);
					dbwr.execSQL(res);
					dbwr.close();
					setContentView(R.layout.myworkspace_item_mark);
					TextView textview_name = (TextView) findViewById(R.id.textview_name);
					textview_name.setText(name + "的成绩单");
					TextView textview_mark = (TextView) findViewById(R.id.textview_mark);
					textview_mark.setText(words);
					db.close();
					scalpeldb.close();
				}
			} else {
				martotal += Communication.martotal / 5;
				marhand += Communication.marhand / 5;
				marvetical += Communication.marvetical / 5;
				marangle += Communication.marangle / 5;
				marleng += Communication.marleng / 5;
				marforce += Communication.marforce / 5;
				martime += Communication.martime / 5;
				String RE = cursor.getString(COL_require);
				nowmsg++;
				require.setText(RE + nowmsg);
				Communication.ReceiveStart();
				myThread m = new myThread();
				t = new Thread(m);
				t.start();
			}
			super.handleMessage(msg);
		}
	}

	class myThread implements Runnable {
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {

				Message message = new Message();
				message.what = nowmsg;
				if (Communication.getReceive() == false) {
					StudentHomeworkActivity.this.myHandler.sendMessage(message);
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

	class mark {
		int id;
		double marhand, marvetical, marangle, marforce, marleng, martime,
				martotal;

		mark(int _id, double _mh, double _mv, double _ma, double _mf,
				double _ml, double _mti, double _mt) {
			this.id = _id;
			this.marhand = _mh;
			this.marvetical = _mv;
			this.marangle = _ma;
			this.marforce = _mf;
			this.marleng = _ml;
			this.martime = _mti;
			this.martotal = _mt;
		}

		String mytoString() {
			DecimalFormat df = new DecimalFormat("0.0");
			String tmp = "总得分：" + df.format(this.martotal) + "\n手型得分："
					+ df.format(this.marhand) + "    垂直度得分："
					+ df.format(this.marvetical) + "\n倾斜角得分："
					+ df.format(this.marangle) + "    力度得分："
					+ df.format(this.marforce) + "\n切开线长得分："
					+ df.format(this.marleng) + "    时间得分："
					+ df.format(this.martime);
			return tmp;
		}
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_screenshot: {
			if (ScreenShotUtils.shotBitmap(StudentHomeworkActivity.this)) {
				Toast.makeText(StudentHomeworkActivity.this, "保存成功",
						Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(StudentHomeworkActivity.this, "保存失败",
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
		case R.id.button_back: {
			onBackPressed();
			break;
		}
		}
	}

	@Override
	public void freshAction(double length) {
		// TODO Auto-generated method stub

	}
}
