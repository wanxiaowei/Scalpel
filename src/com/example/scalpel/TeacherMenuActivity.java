package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TeacherMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teachermenu);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_edit: {
			Intent intent=new Intent(TeacherMenuActivity.this,TeacherEditActivity.class);
			startActivity(intent);
		}
		case R.id.button_pos: {

		}
		}
	}
}
