package com.example.scalpel;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TeacherEditInsertActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachereditinsert);
	}
	public void onClick(View view){
		switch(view.getId()){
		case R.id.button_sure:{
			ScalpelDB scalpelDB=new ScalpelDB(this);
			String sql="('";
			sql+=((EditText)findViewById(R.id.edittext_require)).getText().toString();
			sql+="',";
			sql+=((EditText)findViewById(R.id.edittext_hand)).getText().toString();
			sql+=",";
			sql+=((EditText)findViewById(R.id.edittext_angle)).getText().toString();
			sql+=",";
			sql+=((EditText)findViewById(R.id.edittext_force)).getText().toString();
			sql+=",";
			sql+=((EditText)findViewById(R.id.edittext_length)).getText().toString();
			sql+=",";
			sql+=((EditText)findViewById(R.id.edittext_time)).getText().toString();
			sql+=")";
			scalpelDB.myinsert("problem", sql);
			break;
		}
		case R.id.button_back:{
			onBackPressed();
			break;
		}
		}
	}
}
