package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TeacherEditInsertActivity extends Activity {
	EditText require,hand,angle,force,length,time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachereditinsert);
		require=(EditText)findViewById(R.id.edittext_require);
		hand=(EditText)findViewById(R.id.edittext_hand);
		angle=(EditText)findViewById(R.id.edittext_angle);
		force=(EditText)findViewById(R.id.edittext_force);
		length=(EditText)findViewById(R.id.edittext_length);
		time=(EditText)findViewById(R.id.edittext_time);
		require.setHint("请输入本操作题的题面，如：");
		hand.setHint("请输入该题的标准手型代号(整数1,2,3,4)：1.执弓式，2.握持式，3.执笔式，4.反挑式");
		angle.setHint("请输入该题标准倾斜角(单位°，整数)，如：45");
		force.setHint("请输入该题标准力度(单位g，整数)，如：100");
		length.setHint("请输入该题标准切开线长(单位cm，整数)，如：4");
		time.setHint("请输入该题标准完成时间(单位s，整数)，如：10");
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
			scalpelDB.close();
			Intent intent=new Intent(TeacherEditInsertActivity.this,TeacherMenuActivity.class);
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
