package com.example.scalpel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ScalpelDB extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "scalpel.db";
	private final static int DATABASE_VERSION = 2;
	private final static String TABLE_PROBLEM = "problem";
	private final static String TABLE_HOMEWORK = "homework";

	public ScalpelDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql;
		sql = "CREATE TABLE "
				+ TABLE_PROBLEM
				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT,require VARCHAR(100),hand INTEGER,angle INTEGER,force INTEGER,length INTEGER,time INTEGER)";
		db.execSQL(sql);
		sql = "CREATE TABLE "
				+ TABLE_HOMEWORK
				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT,state VARCHAR(100),problem_id VARCHAR(100))";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		if (arg1 != arg2) {
			String sql;
			sql = "DROP TABLE IF EXISTS " + TABLE_PROBLEM;
			db.execSQL(sql);
			sql = "DROP TABLE IF EXISTS " + TABLE_HOMEWORK;
			db.execSQL(sql);
			onCreate(db);
		}
	}

	public void myinsert(String TABLE_NAME, String option) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql;
		sql = "INSERT INTO " + TABLE_NAME
				+ " (require,hand,angle,force,length,time) " + " VALUES "
				+ option;
		db.execSQL(sql);
		db.close();
	}

	public void mydelete(String TABLE_NAME, String col, String option) {
		SQLiteDatabase db = this.getWritableDatabase();
		String sql;
		sql = "DELETE FROM " + TABLE_NAME + " WHERE " + col + " = " + option;
		db.execSQL(sql);
		db.close();
	}
}
