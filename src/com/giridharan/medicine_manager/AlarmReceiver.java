package com.giridharan.medicine_manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
       // Toast.makeText(context, "I'm running",Toast.LENGTH_SHORT).show();
    	if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")){
        Intent service1 = new Intent(context, MyAlarmService.class);
        context.startService(service1);
        }
    }
}
