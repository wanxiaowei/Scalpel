package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StudentMenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studentmenu);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_guide: {
			Intent intent = new Intent(StudentMenuActivity.this,
					StudentGuideActivity.class);
			startActivity(intent);

		}
		case R.id.button_practice: {

		}
		case R.id.button_homework: {

		}
		}
	}
}
