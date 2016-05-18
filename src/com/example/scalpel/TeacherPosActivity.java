package com.example.scalpel;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class TeacherPosActivity extends Activity {
	int num;
	int[] pro = new int[100];
	ArrayList<TextView> tv=new ArrayList();
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacherpos);
		LinearLayout layout = (LinearLayout) findViewById(R.id.list);
		layout.removeAllViews();
		ScalpelDB scalpeldb = new ScalpelDB(this);
		SQLiteDatabase db = scalpeldb.getReadableDatabase();
		Cursor cursor = db.rawQuery("SELECT * FROM problem", null);
		num=0;
		while (cursor.moveToNext()) {
			int problem_id = cursor.getInt(0);
			pro[num]=problem_id;
			String problem_require = cursor.getString(1);
			/*View view = View.inflate(this, R.layout.item, layout);
			TextView textview = (TextView) view.findViewById(R.id.item_name);
			textview.setText(problem_id + ". " + problem_require);*/
			tv.add(new TextView(this));
			if(num%2==0) tv.get(num).setBackgroundColor(Color.LTGRAY);
			else tv.get(num).setBackgroundColor(Color.WHITE);
			tv.get(num).setTextColor(Color.BLACK);
			tv.get(num).setGravity(Gravity.CENTER);
			tv.get(num).setTag(1);
			tv.get(num).setTextSize(20);
			tv.get(num).setOnClickListener(listener);
			tv.get(num).setText(problem_id+". "+problem_require);
			LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
			lp.gravity=Gravity.CENTER_VERTICAL;
			layout.addView(tv.get(num),lp);
			num++;
		}
		scalpeldb.close();
	}
	private OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			TextView t=(TextView) arg0;
			int tag=(Integer)t.getTag();
			if(tag==1){
				t.setTextColor(Color.RED);
				t.setTag(2);
			}
			else if(tag==2){
				t.setTextColor(Color.BLACK);
				t.setTag(1);
			}
		}
	};
	public void onClick(View view){
		switch(view.getId()){
		case R.id.button_back:{
			onBackPressed();
			break;
		}
		case R.id.button_sure:{
			ScalpelDB scalpeldb=new ScalpelDB(this);
			SQLiteDatabase dbtp=scalpeldb.getWritableDatabase();
			dbtp.execSQL("DELETE FROM homework");
			dbtp.close();
			for(int i=0;i<num;i++){
				int tag=(Integer)tv.get(i).getTag();
				if(tag==2)
				{
					String sql=" (";
					sql+=pro[i];
					sql+=",'";
					SQLiteDatabase db = scalpeldb.getReadableDatabase();
					String sqltp = "SELECT * FROM problem WHERE id = \"" + pro[i] + "\"";
					Cursor cursor = db.rawQuery(sqltp, null);
					cursor.moveToFirst();
					sql+=cursor.getString(cursor.getColumnIndex("require"));
					sql+="') ";
					scalpeldb.myinsert("homework", sql);
					System.out.println(sql);
					db.close();
				}
			}
			scalpeldb.close();
			onBackPressed();
			break;
		}
		}
	}
}
