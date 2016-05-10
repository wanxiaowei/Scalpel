package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class StudentPracticeActivity extends Activity {
	int num;
	int[] pro = new int[100];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studentpractice);
		LinearLayout layout = (LinearLayout) findViewById(R.id.list);
		layout.removeAllViews();
		ScalpelDB scalpeldb = new ScalpelDB(this);
		SQLiteDatabase db = scalpeldb.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM problem", null);
		num=0;
		while (cursor.moveToNext()) {
			num++;
			int problem_id = cursor.getInt(0);
			pro[num]=problem_id;
			String problem_require = cursor.getString(1);
			/*View view = View.inflate(this, R.layout.item, layout);
			TextView textview = (TextView) view.findViewById(R.id.item_name);
			textview.setText(problem_id + ". " + problem_require);*/
			
			TextView tv = new TextView(this);
			if(num%2==1) tv.setBackgroundColor(Color.GRAY);
			else tv.setBackgroundColor(Color.WHITE);
			tv.setId(num);
			tv.setTextSize(20);
			tv.setOnClickListener(listener);
			tv.setText(problem_id+". "+problem_require);
			LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			layout.addView(tv,lp);
		}
		scalpeldb.close();
	}
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(StudentPracticeActivity.this,
					StudentPracticeWorkspaceActivity.class);
			intent.putExtra("problem_id", pro[arg0.getId()]);
			startActivity(intent);
		}
	};
}
