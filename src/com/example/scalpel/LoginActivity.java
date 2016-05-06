package com.example.scalpel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	public static int D = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		Intent intent=new Intent(LoginActivity.this,ScalpelService.class);
		startService(intent);
	}

	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.button_login: {
			String username = ((EditText) findViewById(R.id.edittext_username))
					.getText().toString();
			String password = ((EditText) findViewById(R.id.edittext_password))
					.getText().toString();
			if (username.equals("teacher") && password.equals("teacher")) {
				Intent intent = new Intent(LoginActivity.this,
						TeacherMenuActivity.class);
				startActivity(intent);
			} else if (username.equals("student") && password.equals("student")) {
				Intent intent = new Intent(LoginActivity.this,
						StudentMenuActivity.class);
				startActivity(intent);
			} else {
				EditText editText = ((EditText) findViewById(R.id.edittext_password));
				editText.setError("”√ªß√˚√‹¬Î¥ÌŒÛ");
				editText.requestFocus();
			}
			break;
		}
		case R.id.text_teacher: {
			((EditText) findViewById(R.id.edittext_username))
					.setText("teacher");
			((EditText) findViewById(R.id.edittext_password))
					.setText("teacher");
			break;
		}
		case R.id.text_student: {
			((EditText) findViewById(R.id.edittext_username))
					.setText("student");
			((EditText) findViewById(R.id.edittext_password))
					.setText("student");
			break;
		}
		}
	}
}
