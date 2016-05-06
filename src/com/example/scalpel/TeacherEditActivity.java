package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class TeacherEditActivity extends Activity {
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
		while (cursor.moveToNext()) {
			int problem_id = cursor.getInt(0);
			String problem_require = cursor.getString(1);
			/*View view = View.inflate(this, R.layout.item, layout);
			TextView textview = (TextView) view.findViewById(R.id.item_name);
			textview.setText(problem_id + ". " + problem_require);*/
			
			TextView tv = new TextView(this);
			tv.setText(problem_id+". "+problem_require);
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layout.addView(tv,lp);
		}
		scalpeldb.close();
	}

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
