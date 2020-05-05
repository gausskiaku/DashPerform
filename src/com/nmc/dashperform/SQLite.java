package com.nmc.dashperform;

import java.util.List;

import Dao.Dao;
import Dao.Network_KPIDAO;
import Database.DatabaseHelper;
import Entity.Network;
import Entity.RevenuTraffic;
import Factory.AbstractDAOFactory;
import Factory.FactoryType;
import alarme.AlarmManagerBroadcastReceiver;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SQLite extends Activity {
	DatabaseHelper db;
	Button saveButton;
	Button allButton;
	Network_KPIDAO kpidao;
	private AlarmManagerBroadcastReceiver alarm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite);
		saveButton = (Button) findViewById(R.id.saveButton);
		allButton = (Button) findViewById(R.id.allButton);
		
		//alarm = new AlarmManagerBroadcastReceiver();
		//startRepeatingTimer();
		
		db = new DatabaseHelper(this);
		
		saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//boolean isSelected = db.insertData("2/1/2016", 1, 2, 3, 4);
				
				kpidao = new Network_KPIDAO(getBaseContext());
				kpidao.delete(getBaseContext());
				boolean res = kpidao.create(getBaseContext());
				saveButton.setText(String.valueOf(res).toString());
				
		//		if(isSelected = true){
		//			Toast.makeText(getBaseContext(), "Okkkkkkk", Toast.LENGTH_LONG).show();
		//		} else {
		//			Toast.makeText(getBaseContext(), "Noooon Mdrrrr", Toast.LENGTH_LONG).show();
		//		}
				
			}
		});
		
		allButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				StringBuffer buffer = new StringBuffer();
			//	List<Network> listNetwork = kpidao.find(getBaseContext());
				int a = 1;
			//	for(Network network : listNetwork){
			//		buffer.append("Num : " + a++ +"\n");
			//		buffer.append("Date Time : " + network.getDateTime()+"\n");
			//		buffer.append("Network : " + network.getNetwork()+"\n");
			//		buffer.append("Erland : " + network.getErlang()+"\n");
			//		//buffer.append("Data : " + network.getData()+"\n");
			//		buffer.append("CDR : " + network.getCDR()+"\n");
			//		buffer.append("CSSR : " + network.getCSSR()+"\n");
			//		buffer.append("Availability : " + network.getAvail()+"\n");
			//		buffer.append("Revenu : " + network.getRevenu()+"\n");
			//		buffer.append("\n"+ listNetwork.toString());
			//	}
				
				//if(res.getCount() == 0){
				//	showMessage("Resultat", "Rien Trouver");
				//	return;
				//} 
				//StringBuffer buffer = new StringBuffer();
				//while(res.moveToNext()){
				//	buffer.append("Id : " + res.getString(0)+"\n");
				//	buffer.append("Traffic All : " + res.getInt(1)+"\n");
				//	buffer.append("Revenu All : " + res.getInt(2)+"\n");
				//	buffer.append("Traffic RCS : " + res.getInt(3)+"\n");
				//	buffer.append("Revenu RCS : " + res.getInt(4)+"\n");
				//	buffer.append("\n");
					
				//}
				kpidao = new Network_KPIDAO(getBaseContext());
				Cursor res = kpidao.trouver(getBaseContext());
				while(res.moveToNext()){
					buffer.append("Num : " + a++ +"\n");
							buffer.append("Date Time : " + res.getString(0)+"\n");
							buffer.append("Network : " + res.getString(1)+"\n");
							buffer.append("Erland : " + res.getDouble(2)+"\n");
							//buffer.append("Data : " + network.getData()+"\n");
							buffer.append("CDR : " + res.getDouble(3)+"\n");
							buffer.append("CSSR : " + res.getDouble(4)+"\n");
							buffer.append("Availability : " + res.getDouble(5)+"\n");
							buffer.append("Revenu : " + res.getDouble(6)+"\n");
							buffer.append ("\n");
				}
				
				showMessage("Data", buffer.toString());
				
			}
		});
		
	}
	
	
	
	private void showMessage(String title, String Message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(Message);
		builder.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite, menu);
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
	
	
	public void SaveUpdate() {
		Dao<RevenuTraffic> daoTraffic = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY).getRevenuTraffic(SQLite.this);
		
		
		RevenuTraffic revenuTraffic = new RevenuTraffic();
		revenuTraffic.setDate("2016-*-*");
		revenuTraffic.setRevenu_all(0);
		revenuTraffic.setTraffic_all(0);
		revenuTraffic.setRevenu_rcs(0);
		revenuTraffic.setTraffic_rcs(0);
		
		//revenuTraffic = daoTraffic.create(revenuTraffic);  // After booleen
	}
}
