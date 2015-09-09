package com.giridharan.medicine_manager;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;
@SuppressLint("ServiceCast")
public class MyAlarmService extends Service {
	 private NotificationManager mManager;
	@Override

public void onCreate() {

// TODO Auto-generated method stub

Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();

}
@Override

public IBinder onBind(Intent intent) {

// TODO Auto-generated method stub

Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();

return null;

}



@Override

public void onDestroy() {

// TODO Auto-generated method stub

super.onDestroy();

Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();

}
@Override

public void onStart(Intent intent, int startId) {

// TODO Auto-generated method stub

super.onStart(intent, startId);

Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();
//from here

    mManager = (NotificationManager) this.getApplicationContext().getSystemService(this.getApplicationContext().NOTIFICATION_SERVICE);
    Intent intent1 = new Intent(this.getApplicationContext(),MainActivity.class);
  
    Notification notification = new Notification(R.drawable.ic_launcher,"", System.currentTimeMillis());
    intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);

    PendingIntent pendingNotificationIntent = PendingIntent.getActivity( this.getApplicationContext(),0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
    notification.flags |= Notification.FLAG_AUTO_CANCEL;
    notification.setLatestEventInfo(this.getApplicationContext(),"Have taken Your Medicine?"," Take Care of Your Health", pendingNotificationIntent);

    mManager.notify(0, notification);
    getValuesFromDatabase();
//to here
}
public String[] nameList , balance;
public String name="",bal="",phno="";
private void getValuesFromDatabase() {
	// TODO Auto-generated method stub
	Toast.makeText(getApplicationContext(), "In Database", Toast.LENGTH_LONG).show();
	RegisterAdapter sdba = new RegisterAdapter(MyAlarmService.this);

	sdba.open();
	Cursor cr = sdba.fetch();
	Cursor cr1 = sdba.cn_fetch();
	if (cr.getCount() > 0) {
		int i;
		String name1="",bal1=""; 
		//nameList = new String[cr.getCount()];
		//balance = new String[cr.getCount()];
		//times =new String[cr.getCount()];
		//tt=new String[cr.getCount()];
		for (i = 0; i < cr.getCount(); i++) {

			cr.moveToPosition(i);

			String userNameStr = cr.getString(cr.getColumnIndex(RegisterAdapter.UName));
			String Bal		= cr.getString(cr.getColumnIndex(RegisterAdapter.Tot));
			String M		= cr.getString(cr.getColumnIndex(RegisterAdapter.Mor));
			String Af		= cr.getString(cr.getColumnIndex(RegisterAdapter.Af));
			String N		= cr.getString(cr.getColumnIndex(RegisterAdapter.N));
			String c		= cr.getString(cr.getColumnIndex(RegisterAdapter.TT));
			
			//nameList[i] = userNameStr;
			name1 = name1+" "+userNameStr;
			bal1= bal1+" "+Bal;
			//balance[i]=Bal;
			//tt[i]=c;
			//times[i] =""+M+"-"+Af+"-"+N; 
					}
		name = name1+" " +bal1;
		Toast.makeText(getApplicationContext(),"Here is  :  "+name , Toast.LENGTH_LONG).show();
	}
	
	if (cr1.getCount() > 0) {
		int i;
		//nameList = new String[cr.getCount()];
		//balance = new String[cr.getCount()];
		//times =new String[cr.getCount()];
		//tt=new String[cr.getCount()];
		String userNameStr=null;
		for (i = 0; i < cr1.getCount(); i++) {

			cr1.moveToPosition(i);

			userNameStr= cr1.getString(cr1.getColumnIndex(RegisterAdapter.Cn));
		}
		phno = phno+userNameStr;
		sendSMSMessage(phno,name);
	}
	Toast.makeText(getApplicationContext(),"Here is phone no :  "+phno , Toast.LENGTH_LONG).show();
}
protected void sendSMSMessage(String phoneNo,String message) {
    
    try {
       SmsManager smsManager = SmsManager.getDefault();
       smsManager.sendTextMessage(phoneNo, null, message, null, null);
       Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
    } 
    
    catch (Exception e) {
       Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
       e.printStackTrace();
    }
 }

private void Notify(String notificationTitle, String notificationMessage){
    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    @SuppressWarnings("deprecation")
    
    Notification notification = new Notification(R.drawable.ic_launcher,"Have You Taken Tablet", System.currentTimeMillis());
    Intent notificationIntent = new Intent(this,NotificationView.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
    
    //notification.setLatestEventInfo(, notificationTitle,notificationMessage, pendingIntent);
    notificationManager.notify(9999, notification);
 }



@Override

public boolean onUnbind(Intent intent) {

// TODO Auto-generated method stub

Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();

return super.onUnbind(intent);

}
}