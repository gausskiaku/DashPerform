package alarme;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.github.mikephil.charting.data.Entry;
import com.nmc.dashperform.MainActivity;
import com.nmc.dashperform.R;
import com.nmc.dashperform.Rev_Traf;
import com.nmc.dashperform.SQLite;
import com.nmc.dashperform.SliderTab;
import Database.DatabaseHelper;
import Entity.Network;
import Metier.Network_KPI;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver{

	final public static String ONE_TIME = "onetime";
	NotificationManager nm;
	Network_KPI network = new Network_KPI();
	
	
	@SuppressLint({ "SimpleDateFormat", "Wakelock" })
	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Context context, Intent intent) {
	
		if(traitementDate(context) == true){
		
		nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		CharSequence from = "Performance";
		CharSequence message = "Data has been updated";
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, SliderTab.class), 0);
		Notification notif = new Notification(R.drawable.notification, "Performance NMC", System.currentTimeMillis());
		notif.setLatestEventInfo(context, from, message, contentIntent);
		notif.vibrate = new long[] {0,200,100,200,100,200}; 
		nm.notify(1, notif);
		  
	//	SQLite sqLite = new SQLite();
	//	sqLite.SaveUpdate();
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE); 
		PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG"); 
		wl.acquire();
		

		Bundle extras = intent.getExtras(); 
		StringBuilder msgStr = new StringBuilder(); 
		

		if(extras != null && extras.getBoolean(ONE_TIME, Boolean.FALSE)){ 
			//Make sure this intent has been sent by the one-time timer button. 
			msgStr.append("One time Timer : "); 
		} 

		Format formatter = new SimpleDateFormat("hh:mm:ss a"); 
		msgStr.append(formatter.format(new Date())); 
		Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
		wl.release();	
		
		}
	 }
	
	Calendar calendar = Calendar.getInstance();
	// DatabaseHelper db;
	
		public void SetAlarm(Context context) { 
			AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE); 
			Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class); 
			intent.putExtra(ONE_TIME, Boolean.FALSE); 
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0); 
			calendar.set(Calendar.HOUR, 8);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.AM_PM, Calendar.AM);
	    //	After after 24 heurs 
		
			am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pi);	
		} 
		

		public void CancelAlarm(Context context){ 

			Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class); 
			PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0); 
			AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE); 
			alarmManager.cancel(sender); 
		} 
		

		public void setOnetimeTimer(Context context){ 
			AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE); 
			Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class); 
			intent.putExtra(ONE_TIME, Boolean.TRUE); 
			PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0); 
			am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi); 
		} 
		
		
		@SuppressLint("SimpleDateFormat")
		private boolean traitementDate(Context context) {
			// Date Systeme
			SimpleDateFormat formater = null;
			Date aujourdhui = new Date();
			formater = new SimpleDateFormat("yy-MM-dd");
			String dateSysteme = formater.format(aujourdhui);
			
			
			// Date Base de Donnees
			Network_KPI network = new Network_KPI();
			String dateUpdate = network.getDateUpdate(context);
			
			if (dateUpdate == dateSysteme){
				return true;
			} else {
				return false;
			}
			}
		
}
