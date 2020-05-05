package com.nmc.dashperform;

import Zoom.MyImageView;
import Zoom.ZoomableViewGroup;
import alarme.AlarmManagerBroadcastReceiver;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Zoom extends Activity {

	private AlarmManagerBroadcastReceiver alarm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//View view = new ZoomableViewGroup(this);
		//view = getLayoutInflater().inflate(R.layout.activity_zoom, null); 
		setContentView(R.layout.activity_zoom);
		alarm = new AlarmManagerBroadcastReceiver();
		
		
	}
@Override
protected void onStart() {
	// TODO Auto-generated method stub
	super.onStart();
}
	
		public void startRepeatingTimer(View view) { 
		     	Context context = this.getApplicationContext(); 
		     	if(alarm != null){ 
		     		alarm.SetAlarm(context); 
		    	}else{ 
		     		Toast.makeText(context, "Alarm is null", Toast.LENGTH_LONG).show(); 
		     	} 
		     } 
		
		 public void cancelRepeatingTimer(View view){ 
			      	Context context = this.getApplicationContext(); 
			      	if(alarm != null){ 
			      		alarm.CancelAlarm(context); 
			      	}else{ 
			      		Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show(); 
			      	} 
			      } 

		 public void onetimeTimer(View view){ 
			      	Context context = this.getApplicationContext(); 
			      	if(alarm != null){ 
			      		alarm.setOnetimeTimer(context); 
			      	}else{ 
			     		Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show(); 
			      	} 
			      } 

}
