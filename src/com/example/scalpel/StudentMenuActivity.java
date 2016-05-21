package com.example.scalpel;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class StudentMenuActivity extends Activity {
	EditText tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studentmenu);
		// getIntent().getStringExtra("action");
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_guide: {
			Intent intent = new Intent(StudentMenuActivity.this,
					StudentGuideActivity.class);
			// intent.putExtra("action", "123");

			startActivity(intent);
			break;

		}
		case R.id.button_practice: {
			Intent intent = new Intent(StudentMenuActivity.this,
					StudentPracticeActivity.class);
			startActivity(intent);
			break;
		}
		case R.id.button_homework: {
			tv=new EditText(this);
			Dialog alertDialog = new AlertDialog.Builder(this).
					setTitle("请输入你的姓名").
					setIcon(android.R.drawable.ic_dialog_info).
					setView(tv).
					setPositiveButton("确定", 
							new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									Intent intent=new Intent(StudentMenuActivity.this,StudentHomeworkActivity.class);
									intent.putExtra("name", tv.getText().toString());
									startActivity(intent);
								}
							}).
					setNegativeButton("取消", 
							new DialogInterface.OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									
								}
							}).create();
			alertDialog.show();
		}
		}
	}
}
