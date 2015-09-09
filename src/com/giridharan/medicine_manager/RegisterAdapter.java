package com.giridharan.medicine_manager;
import android.app.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.provider.BaseColumns;
import android.util.Log;

public class RegisterAdapter extends AbstractAdapter implements BaseColumns {

	public RegisterAdapter(Context ctx) {
		super(ctx);
	}
	public static final String ID="ID";
	public static final String TABLENAME = "MREG03";
	public static final String TABLENAME1 = "Contacts";
	public static final String TABLENAME2 = "Lastupdate";
	public static final String UName = "Name";
	public static final String BF = "BF";
	public static final String Mor = "Mor";
	public static final String Af = "Af";
	public static final String N = "N";
	public static final String Tot = "tot";
	public static final String Amt = "amt";
	public static final String TT ="tt";
	public static final String Cn="cn";
	public static final String Lastup="lp";
	
	
	
	Cursor mCursor;
	//checkDB
	public long lp_insert(String Name,String lp,String tot) {

		ContentValues args = new ContentValues();

		args.put(UName, Name);
		args.put(Lastup, lp);
		args.put(Tot, tot);
		
		return mDb.insert(TABLENAME2, null, args);

	}
	public Cursor lp_fetch() throws SQLException {

		String selectquery = "Select * from " + TABLENAME2 + ";";
		
		mCursor = mDb.rawQuery(selectquery, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public long lp_update(String ID1,String Name,String lp,String tot) {

		ContentValues args = new ContentValues();

		args.put(UName, Name);
		args.put(Lastup, lp);
		args.put(Tot, tot);
				return mDb.update(TABLENAME2, args,"ID=?",new String[] {ID1});

	}

	public long lp_delete(String ID){
		ContentValues args = new ContentValues();
		//sqlDB.delete(TodoTable.TABLE_TODO, selection,selectionArgs);
		//return mDb.delete(TABLENAME,"ID=?",new String[] {ID});
		return mDb.delete(TABLENAME2,"ID=?",new String[]{ID});
		
		
	}

	
	
	//Contacts DB
	public long cn_insert(String cn) {

		ContentValues args = new ContentValues();

		//args.put(UName, Name);
		args.put(Cn, cn);
		
		return mDb.insert(TABLENAME1, null, args);

	}
	public Cursor cn_fetch() throws SQLException {

		String selectquery = "Select * from " + TABLENAME1 + ";";
		
		mCursor = mDb.rawQuery(selectquery, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public long cn_update(String ID1,String Name,String cn) {

		ContentValues args = new ContentValues();

		args.put(ID, ID1);
		args.put(UName, Name);
		args.put(Cn, cn);
				return mDb.update(TABLENAME1, args,"ID=?",new String[] {ID1});

	}

	public long cn_delete(String ID){
		ContentValues args = new ContentValues();
		//sqlDB.delete(TodoTable.TABLE_TODO, selection,selectionArgs);
		//return mDb.delete(TABLENAME,"ID=?",new String[] {ID});
		return mDb.delete(TABLENAME1,"ID=?",new String[]{ID});
		
		
	}

	
//General DB

	public long insert(String Name,String BF1, String Mor1,String Af1,String N1,String tot1, String amt1,String tt1) {

		ContentValues args = new ContentValues();

		args.put(UName, Name);
		args.put(BF, BF1);
		args.put(Mor,Mor1);
		args.put(Af, Af1);
		args.put(N, N1);
		args.put(Tot, tot1);
		args.put(Amt, amt1);
		args.put(TT,tt1);
		
		return mDb.insert(TABLENAME, null, args);

	}
	
	public Cursor fetch() throws SQLException {

		String selectquery = "Select * from " + TABLENAME + ";";
		
		mCursor = mDb.rawQuery(selectquery, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	public long update(String ID1,String Name,String Rtablet,String Ntime) {

		ContentValues args = new ContentValues();

		args.put(UName, Name);
		args.put(Tot, Rtablet);
		args.put(TT,Ntime);
		
		

		return mDb.update(TABLENAME, args,"ID=?",new String[] {ID1});

	}

	public long delete(String ID){
		ContentValues args = new ContentValues();
		//sqlDB.delete(TodoTable.TABLE_TODO, selection,selectionArgs);
		//return mDb.delete(TABLENAME,"ID=?",new String[] {ID});
		return mDb.delete(TABLENAME,"ID=?",new String[]{ID});
		
		
	}
}
