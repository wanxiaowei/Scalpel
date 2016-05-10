package com.example.scalpel;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class StudentPracticeWorkspaceActivity extends Activity {
	TextView require,hand,angle,force,length;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myworkspace);
		/*LinearLayout layout = (LinearLayout) findViewById(R.id.workspace_tool);
		layout.removeAllViews();
		View view = View
				.inflate(this, R.layout.myworkspace_item_button, layout);*/
		LinearLayout layout = (LinearLayout) findViewById(R.id.workspace_tool);
		layout.removeAllViews();
		View viewtext = View.inflate(this, R.layout.myworkspace_item_text,
				layout);
		require = (TextView) findViewById(R.id.textview_require);
		hand = (TextView) findViewById(R.id.textview_status_hand);
		angle = (TextView) findViewById(R.id.textview_status_angle);
		force = (TextView) findViewById(R.id.textview_status_force);
		length = (TextView) findViewById(R.id.textview_status_length);
		require.setText("要求：");
		hand.setText("手型：");
		angle.setText("倾斜角：");
		force.setText("力度：");
		length.setText("长度：");
		ScalpelDB scalpeldb=new ScalpelDB(this);
		int tmp=getIntent().getIntExtra(("problem_id"),0);
		SQLiteDatabase db = scalpeldb.getReadableDatabase();
		String sql="SELECT * FROM problem where id="+tmp;
		Cursor cursor = db.rawQuery(sql, null);
		require.setText(cursor.getString(1));
		scalpeldb.close();
	}
}
