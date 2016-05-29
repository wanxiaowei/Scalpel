package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class TeacherEditActivity extends Activity {
	int num;
	int[] pro = new int[100];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacheredit);
		LinearLayout layout = (LinearLayout) findViewById(R.id.list);
		layout.removeAllViews();
		ScalpelDB scalpeldb = new ScalpelDB(this);
		SQLiteDatabase db = scalpeldb.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM problem", null);
		num = 0;
		while (cursor.moveToNext()) {
			num++;
			int problem_id = cursor.getInt(0);
			pro[num] = problem_id;
			String problem_require = cursor.getString(1);
			/*
			 * View view = View.inflate(this, R.layout.item, layout); TextView
			 * textview = (TextView) view.findViewById(R.id.item_name);
			 * textview.setText(problem_id + ". " + problem_require);
			 */

			TextView tv = new TextView(this);
			if (num % 2 == 1)
				tv.setBackgroundColor(Color.LTGRAY);
			else
				tv.setBackgroundColor(Color.WHITE);
			tv.setGravity(Gravity.CENTER);
			tv.setTag(num);
			tv.setTextSize(20);
			tv.setOnClickListener(listener);
			tv.setText(num + ". " + problem_require);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
			lp.gravity = Gravity.CENTER_VERTICAL;
			layout.addView(tv, lp);
		}
		scalpeldb.close();
	}

	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(TeacherEditActivity.this,
					TeacherEditUpdateActivity.class);
			intent.putExtra("problem_id", pro[(Integer) arg0.getTag()]);
			startActivity(intent);
		}
	};

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_insert: {
			Intent intent = new Intent(TeacherEditActivity.this,
					TeacherEditInsertActivity.class);
			startActivity(intent);
			break;
		}
		}
	}
}
