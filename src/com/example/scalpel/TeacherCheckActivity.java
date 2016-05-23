package com.example.scalpel;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class TeacherCheckActivity extends Activity {
	String sql;
	Cursor cursor;
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;    
    private final int MP = ViewGroup.LayoutParams.MATCH_PARENT; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachercheck);
		TableLayout tableLayout = (TableLayout)findViewById(R.id.table_mark);
		tableLayout.setStretchAllColumns(true);
		ScalpelDB scalpelDB=new ScalpelDB(this);
		SQLiteDatabase db=scalpelDB.getReadableDatabase();
		sql="SELECT * FROM homework";
		cursor=db.rawQuery(sql, null);
		int n=cursor.getCount();
		TableRow rowtitle=new TableRow(this);
		TextView tvtitle=new TextView(this);
		tvtitle.setText("ÐÕÃû");
		tvtitle.setGravity(Gravity.CENTER);
		rowtitle.addView(tvtitle);
		for(int i=1;i<=n;i++){
			TextView tv=new TextView(this);
			tv.setText("ÌâÄ¿"+i);
			tv.setGravity(Gravity.CENTER);
			rowtitle.addView(tv);
		}
		tableLayout.addView(rowtitle,new TableLayout.LayoutParams(MP, WC));
		sql="SELECT * FROM result";
		cursor=db.rawQuery(sql, null);
		//cursor.moveToFirst();
		int num=0;
		while(cursor.moveToNext()){
			num++;
			System.out.println(num+"  sss   "+n);
			TableRow row=new TableRow(this);
			for(int i=1;i<=n+1;i++){
				String tmp=cursor.getString(i).toString();
				TextView tv=new TextView(this);
				tv.setText(tmp);
				tv.setGravity(Gravity.CENTER);
				if(num%2==1) tv.setBackgroundColor(Color.LTGRAY);
				row.addView(tv, new TableRow.LayoutParams(MP,MP));
				row.setGravity(Gravity.CENTER);
			}
			tableLayout.addView(row,new TableLayout.LayoutParams(MP, MP));
		}
	}
	public void onClick(View view){
		switch(view.getId()){
		case R.id.button_back:{
			onBackPressed();
			break;
		}
		}
	}
}
