package com.giridharan.medicine_manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class AbstractAdapter {

	protected static final String TAG = "Adapter";
	protected DatabaseHelper mDbHelper;
	protected SQLiteDatabase mDb;
	protected static final String DATABASE_NAME = "Sample.db";
	protected static final int DATABASE_VERSION = 1;

	protected final Context mCtx;

	protected static class DatabaseHelper extends SQLiteOpenHelper {
//Medicine Register
		private static final String MedRegister = "CREATE TABLE IF NOT EXISTS "
				+ RegisterAdapter.TABLENAME + "( "+RegisterAdapter.ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " + RegisterAdapter.UName
				+ " TEXT," + RegisterAdapter.BF + " TEXT,"
				+ RegisterAdapter.Mor + " TEXT," + RegisterAdapter.Af
				+ " TEXT, " + RegisterAdapter.N + " TEXT,"+ RegisterAdapter.Tot + " TEXT," + RegisterAdapter.Amt+ " TEXT, "+RegisterAdapter.TT +" TEXT);";
		private static final String CnRegister = "CREATE TABLE IF NOT EXISTS "
				+ RegisterAdapter.TABLENAME1 + "( "+RegisterAdapter.ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " + RegisterAdapter.UName
				+ " TEXT," + RegisterAdapter.Cn + " TEXT);";
		private static final String lpRegister = "CREATE TABLE IF NOT EXISTS "
				+ RegisterAdapter.TABLENAME2 + "( "+RegisterAdapter.ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " + RegisterAdapter.UName
				+ " TEXT," + RegisterAdapter.Lastup + " TEXT, "+ RegisterAdapter.Tot + " TEXT);";
		
		
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(MedRegister);
			db.execSQL(CnRegister);
			db.execSQL(lpRegister);


		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

			db.execSQL("DROP TABLE IF EXISTS routes"+MedRegister);
			db.execSQL("DROP TABLE IF EXISTS routes"+CnRegister);
			db.execSQL("DROP TABLE IF EXISTS routes"+lpRegister);
			onCreate(db);
		}
	}

	public AbstractAdapter(Context ctx) {
		this.mCtx = ctx;
	}

	public AbstractAdapter open() throws SQLException {
		mDbHelper = new DatabaseHelper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
}