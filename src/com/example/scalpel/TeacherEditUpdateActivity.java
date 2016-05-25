package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TeacherEditUpdateActivity extends Activity {
	EditText require,hand,angle,force,length,time;
	int COL_require,COL_hand,COL_angle,COL_force,COL_length,COL_time;
	int pro_id;
	ScalpelDB scalpelDB;
	String sql;
	Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachereditupdate);
		require=(EditText)findViewById(R.id.edittext_require);
		hand=(EditText)findViewById(R.id.edittext_hand);
		angle=(EditText)findViewById(R.id.edittext_angle);
		force=(EditText)findViewById(R.id.edittext_force);
		length=(EditText)findViewById(R.id.edittext_length);
		time=(EditText)findViewById(R.id.edittext_time);
		pro_id = getIntent().getIntExtra("problem_id", 0);
		scalpelDB=new ScalpelDB(this);
		SQLiteDatabase db=scalpelDB.getReadableDatabase();
		sql="SELECT * FROM problem WHERE id=\""+pro_id+"\";";
		cursor=db.rawQuery(sql, null);
		cursor.moveToFirst();
		COL_require=cursor.getColumnIndex("require");
		COL_hand=cursor.getColumnIndex("hand");
		COL_angle=cursor.getColumnIndex("angle");
		COL_force=cursor.getColumnIndex("force");
		COL_length=cursor.getColumnIndex("length");
		COL_time=cursor.getColumnIndex("time");
		require.setText(cursor.getString(COL_require).toString());
		hand.setText(cursor.getString(COL_hand).toString());
		angle.setText(cursor.getString(COL_angle).toString());
		force.setText(cursor.getString(COL_force).toString());
		length.setText(cursor.getString(COL_length).toString());
		time.setText(cursor.getString(COL_time).toString());
		db.close();
		scalpelDB.close();
	}
	public void onClick(View view){
		switch(view.getId()){
		case R.id.button_update:{
			scalpelDB=new ScalpelDB(this);
			SQLiteDatabase db=scalpelDB.getWritableDatabase();
			sql="UPDATE problem SET ";
			sql=sql+"require = '"+require.getText().toString()+"',";
			sql=sql+"hand = "+hand.getText().toString()+",";
			sql=sql+"angle = "+angle.getText().toString()+",";
			sql=sql+"force = "+force.getText().toString()+",";
			sql=sql+"length = "+length.getText().toString()+",";
			sql=sql+"time = "+time.getText().toString();
			sql=sql+" WHERE id=\""+pro_id+"\";";
			db.execSQL(sql);
			db.close();
			scalpelDB.close();
			Intent intent=new Intent(TeacherEditUpdateActivity.this,TeacherMenuActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.button_delete:{
			scalpelDB=new ScalpelDB(this);
			SQLiteDatabase db=scalpelDB.getWritableDatabase();
			sql="DELETE FROM problem WHERE id=\""+pro_id+"\";";
			db.execSQL(sql);
			db.close();
			scalpelDB.close();
			Intent intent=new Intent(TeacherEditUpdateActivity.this,TeacherMenuActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.button_back:{
			onBackPressed();
			break;
		}
		}
	}
}
