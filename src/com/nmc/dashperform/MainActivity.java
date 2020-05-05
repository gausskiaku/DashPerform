package com.nmc.dashperform;



import java.util.Calendar;

import alarme.AlarmManagerBroadcastReceiver;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends TabActivity {
	private TabHost tabHost;
	private AlarmManagerBroadcastReceiver alarm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// view = getLayoutInflater().inflate(R.layout.activity_two_g,null); 
		// onetimeTimer(view);
		alarm = new AlarmManagerBroadcastReceiver();
		this.tabHost = getTabHost();
		startRepeatingTimer();
		 setupTab("Revenu", "tab1", new Intent().setClass(this, Rev_Traf.class));
	     setupTab("2G KPI", "tab2", new Intent().setClass(this, TwoG.class));
	     setupTab("3G KPI", "tab3", new Intent().setClass(this, ThreeG.class));
	     setupTab("RCS KPI", "tab4", new Intent().setClass(this, Rcs.class));
	}
	
@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
}


public void startRepeatingTimer() { 
 	Context context = this.getApplicationContext(); 
 	if(alarm != null){ 
 		alarm.SetAlarm(context); 
	}else{ 
 		Toast.makeText(context, "Alarm is null", Toast.LENGTH_LONG).show(); 
 	} 
 } 

public void cancelRepeatingTimer(){ 
      	Context context = this.getApplicationContext(); 
      	if(alarm != null){ 
      		alarm.CancelAlarm(context); 
      	}else{ 
      		Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show(); 
      	} 
      } 

public void onetimeTimer(){ 
      	Context context = this.getApplicationContext(); 
      	if(alarm != null){ 
      		alarm.setOnetimeTimer(context); 
      	}else{ 
     		Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show(); 
      	} 
      }

	
	private void setupTab(String name, String tag, Intent intent) {
		tabHost.addTab(tabHost.newTabSpec(tag).setIndicator(createTabView(tabHost.getContext(), name)).setContent(intent));
	}
private static View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
@Override
public void overridePendingTransition(int enterAnim, int exitAnim) {
	// TODO Auto-generated method stub
	super.overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
;
}

}
