package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TeacherPosMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teacherposmenu);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_check: {
			//Intent intent=new Intent(TeacherPosMenuActivity.this,TeacherEditActivity.class);
			//startActivity(intent);
			break;
		}
		case R.id.button_pos: {
			Intent intent=new Intent(TeacherPosMenuActivity.this,TeacherPosActivity.class);
			startActivity(intent);
			break;
		}
		}
	}
}
