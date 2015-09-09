package com.giridharan.medicine_manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	protected LocationManager locationManager;
	protected LocationListener locationListener;
	
	protected Context context;
	TextView txtLat;
	String lat;
	String provider;
	
	protected String latitude,longitude; 
	protected boolean gps_enabled,network_enabled;
	ImageView b1,b2,b7,b8,b6;
Button b3,b4,b5;
String Tab;
String num;
private PendingIntent pendingIntent;
TextView t1;
String [] m,a,n;
private static MainActivity inst;
public static MainActivity instance() {
    return inst;
}
@Override
public void onStart() {
    super.onStart();
    inst = this;
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		t1=(TextView)findViewById(R.id.textView2);
		b1=(ImageView)findViewById(R.id.button1);
		b2=(ImageView)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		b6=(ImageView)findViewById(R.id.button6);
		b7=(ImageView)findViewById(R.id.button7);
		b8=(ImageView)findViewById(R.id.button8);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//setting alarm here	
		//to here		

		Intent i = new Intent(MainActivity.this, add_tablet.class);
		startActivity(i);
			}
			});
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		Intent i = new Intent(MainActivity.this, view_tablet_list.class);
		startActivity(i);
			}
			});
		b3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//change to set time from here
				Calendar cal = Calendar.getInstance(); 
				int mYear = cal.get(Calendar.YEAR);
				int mMonth = cal.get(Calendar.MONTH);
				int mDay = cal.get(Calendar.DAY_OF_MONTH);
				int min = cal.get(Calendar.MINUTE);
				        //12 hour format
				  //int hr = cal.get(Calendar.HOUR);
				        //24 hour format
				  int hrd = cal.get(Calendar.HOUR_OF_DAY);
				
				String S ="Last Update : "+ mDay+" /"+mMonth+" /"+mYear+" :: "+hrd+" : "+min;
				t1.setText(S);
				//to here
				
				Tab=null;
				num=null;
				getValuesFromDatabase("M");
//				t1.setText("Yout Final Update :"+dateFormat.format(cal.getTime()));
				Toast.makeText(MainActivity.this,S+"Happy Morning, You have taken Your Morning Tablet",Toast.LENGTH_LONG).show();
				
		
			}
			});
		b4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar cal = Calendar.getInstance(); 
				int mYear = cal.get(Calendar.YEAR);
				int mMonth = cal.get(Calendar.MONTH);
				int mDay = cal.get(Calendar.DAY_OF_MONTH);
				int min = cal.get(Calendar.MINUTE);
				        //12 hour format
				  //int hr = cal.get(Calendar.HOUR);
				        //24 hour format
				  int hrd = cal.get(Calendar.HOUR_OF_DAY);
				
				String S ="Last Update : "+ mDay+" /"+mMonth+" /"+mYear+" :: "+hrd+" : "+min;
				t1.setText(S);
				Tab=null;
				num=null;
				getValuesFromDatabase("Af");
//				t1.setText("Yout Final Update :"+dateFormat.format(cal.getTime()));
				Toast.makeText(MainActivity.this,"Happy Morning, You have taken Your AfterNoon Tablet",Toast.LENGTH_LONG).show();
			}
			});
		b5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar cal = Calendar.getInstance(); 
				int mYear = cal.get(Calendar.YEAR);
				int mMonth = cal.get(Calendar.MONTH);
				int mDay = cal.get(Calendar.DAY_OF_MONTH);
				int min = cal.get(Calendar.MINUTE);
				        //12 hour format
				  //int hr = cal.get(Calendar.HOUR);
				        //24 hour format
				  int hrd = cal.get(Calendar.HOUR_OF_DAY);
				
				String S ="Last Update : "+ mDay+" /"+mMonth+" /"+mYear+" :: "+hrd+" : "+min;
				t1.setText(S);
				Tab=null;
				num=null;
				getValuesFromDatabase("N");
//				t1.setText("Yout Final Update :"+dateFormat.format(cal.getTime()));
				Toast.makeText(MainActivity.this,"Happy Morning, You have taken Your Night Tablet",Toast.LENGTH_LONG).show();
			}
			});
		b6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		Intent i = new Intent(MainActivity.this, c_add.class);
		startActivity(i);
			}
			});
		
		
		//changes from here
		Calendar calendar = Calendar.getInstance();
	     
	      calendar.set(Calendar.MONTH, 5);
	      calendar.set(Calendar.YEAR, 2015);
	      calendar.set(Calendar.DAY_OF_MONTH, 19);
	 
	      calendar.set(Calendar.HOUR_OF_DAY, 7);
	      calendar.set(Calendar.MINUTE, 42);
	      calendar.set(Calendar.SECOND, 0);
	      calendar.set(Calendar.AM_PM,Calendar.PM);
	      Intent myIntent = new Intent(MainActivity.this, MyReceiver.class);
	      pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, myIntent,0);
	    
	 
		b7.setOnClickListener(new View.OnClickListener() {  //cancel
			@Override
			public void onClick(View v) {
				AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

		    	  alarmManager.cancel(pendingIntent);

		
			}
			});
		b8.setOnClickListener(new View.OnClickListener() {		//set alarm
			@Override
			public void onClick(View v) {
				 
			      AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
			      //alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
			    //changes here
			      alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
			    	        AlarmManager.INTERVAL_HALF_HOUR,
			    	        AlarmManager.INTERVAL_HALF_HOUR, pendingIntent);
			}
			});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void getValuesFromDatabase( String ST) {
		// TODO Auto-generated method stub

		RegisterAdapter sdba = new RegisterAdapter(MainActivity.this);
		sdba.open();
		Cursor cr = sdba.fetch();
		if (cr.getCount() > 0) {
			for (int i = 0; i < cr.getCount(); i++) {
				cr.moveToPosition(i);
				String name11 		= cr.getString(cr.getColumnIndex(RegisterAdapter.UName));
				String T1		= cr.getString(cr.getColumnIndex(RegisterAdapter.Tot));
				if(ST.equals("M")){
					System.out.println("1");
				String name 		= cr.getString(cr.getColumnIndex(RegisterAdapter.UName));
				String id 		= cr.getString(cr.getColumnIndex(RegisterAdapter.ID));
				String M		= cr.getString(cr.getColumnIndex(RegisterAdapter.Mor));
				String T		= cr.getString(cr.getColumnIndex(RegisterAdapter.Tot));	
				String TT		= cr.getString(cr.getColumnIndex(RegisterAdapter.TT));
				if(M.equals("1")){int t=Integer.parseInt(T);t--;T=""+t; 
				long value = sdba.update(id,name,T,TT);
				value =sdba.lp_update(id,name,T,TT);
				}
				}
				if(ST.equals("Af")){
					String name 		= cr.getString(cr.getColumnIndex(RegisterAdapter.UName));
					String id 		= cr.getString(cr.getColumnIndex(RegisterAdapter.ID));
					String Af		= cr.getString(cr.getColumnIndex(RegisterAdapter.Af));
					String T		= cr.getString(cr.getColumnIndex(RegisterAdapter.Tot));	
					String TT		= cr.getString(cr.getColumnIndex(RegisterAdapter.TT));
					if(Af.equals("1")){int t=Integer.parseInt(T);t--;T=""+t;
					long value = sdba.update(id,name,T,TT);
					}
					}
				if(ST.equals("N")){
					String name 		= cr.getString(cr.getColumnIndex(RegisterAdapter.UName));
					String id 		= cr.getString(cr.getColumnIndex(RegisterAdapter.ID));
					String N		= cr.getString(cr.getColumnIndex(RegisterAdapter.N));
					String T		= cr.getString(cr.getColumnIndex(RegisterAdapter.Tot));	
					String TT		= cr.getString(cr.getColumnIndex(RegisterAdapter.TT));
					if(N.equals("1")){int t=Integer.parseInt(T);t--;T=""+t; 
					long value = sdba.update(id,name,T,TT);
					}
					}
				
				Tab = Tab+" "+name11;
				num= num+" "+T1;
				
				
						}
		}
		sdba.close();
	}
	}

