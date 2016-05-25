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
		require.setHint("�����뱾����������棬�磺");
		hand.setHint("���������ı�׼���ʹ���(����1,2,3,4)��1.ִ��ʽ��2.�ճ�ʽ��3.ִ��ʽ��4.����ʽ");
		angle.setHint("����������׼��б��(��λ�㣬����)���磺45");
		force.setHint("����������׼����(��λg������)���磺100");
		length.setHint("����������׼�п��߳�(��λcm������)���磺4");
		time.setHint("����������׼���ʱ��(��λs������)���磺10");
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
