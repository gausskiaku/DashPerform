package com.nmc.dashperform;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import Dao.Network_KPIDAO;
import Entity.Network;
import Metier.Network_KPI;
import Metier.Network_KPITwoWeekly;
import Metier.Network_KPIWeekly;
import alarme.AlarmManagerBroadcastReceiver;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class Accueil extends Activity {
	ActionBar actionBar;
	private AlarmManagerBroadcastReceiver alarm;
	private int mYear, mMonth, mDay, mHour, mMinute, mSecond;
	Button bt_daily, bt_weekly, bt_twoWeek, bt_Begin, bt_End; // bt_monthly, bt_period,
	private static ProgressDialog loading;
	AsyncCallWS task = null;
	Network_KPIDAO kpidao;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		
		bt_daily = (Button) findViewById(R.id.bt_daily);
		bt_weekly = (Button) findViewById(R.id.bt_weekly);
	//	bt_monthly = (Button) findViewById(R.id.bt_monthly);
	//	bt_period = (Button) findViewById(R.id.bt_period);
		bt_twoWeek = (Button) findViewById(R.id.bt_twoweekly);
		
		
		
		actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(255, 0, 0)));
		
		actionBar.setTitle("Dash Perform Home");
		
//		ConnectivityManager cmanager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//		NetworkInfo ninfo = cmanager.getActiveNetworkInfo();
//		if(ninfo != null && ninfo.isConnected()){
//			task = new AsyncCallWS(Accueil.this);
//			task.execute(); 	
//		} else {
//			Toast.makeText(Accueil.this, "Please enable mobile network", Toast.LENGTH_LONG).show();
//			Intent intent = new Intent(Accueil.this, Erreur.class);
//			intent.putExtra("ERREUR", "ERREUR");
//			startActivityForResult(intent, RESULT_OK);
//		}
		
		//alarm = new AlarmManagerBroadcastReceiver();
		//startRepeatingTimer();
		

		bt_daily.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//	kpidao = new Network_KPIDAO(getBaseContext());
			//	kpidao.delete(getBaseContext());
			//	boolean res = kpidao.create(getBaseContext());
			//	Intent dailyPerform = new Intent(Accueil.this, SliderTab.class);
			//	startActivity(dailyPerform);
				ConnectivityManager cmanager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
				NetworkInfo ninfo = cmanager.getActiveNetworkInfo();
				if(ninfo != null && ninfo.isConnected()){
					task = new AsyncCallWS(Accueil.this);
					task.execute(); 	
				} else {
					Toast.makeText(Accueil.this, "Please enable mobile network", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(Accueil.this, Erreur.class);
					intent.putExtra("ERREUR", "ERREUR");
					startActivityForResult(intent, RESULT_OK);
				}
			}
		});
		
		bt_weekly.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent weekPerform = new Intent(Accueil.this, SliderTabWeekly.class);
				startActivity(weekPerform);
			}
		});
		
	/*	bt_monthly.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent monthPerform = new Intent(Accueil.this, SliderTabMonthly.class);
				startActivity(monthPerform);
			}
		});
		
		bt_period.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialogSearch();
			}
		});
		
		*/
		bt_twoWeek.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent twoWeekPerform = new Intent(Accueil.this, SliderTabTwoWeekly.class);
				startActivity(twoWeekPerform);
				
			}
		});
		
		
		task = (AsyncCallWS) getLastNonConfigurationInstance();
		
		if(task != null){
			// On lie l'activité à l'AsyncTask
			task.link(this);
		}
	}

	private void dialogSearch() {
		LayoutInflater factory = LayoutInflater.from(this);
	    final View alertDialogView = factory.inflate(R.layout.plage, null);
	    
	    
	   //Création de l'AlertDialog
	     AlertDialog.Builder adb = new AlertDialog.Builder(this);
	     adb.setView(alertDialogView);
	     
	     
	     bt_Begin = (Button) alertDialogView.findViewById(R.id.bt_Begin);
	     bt_End = (Button) alertDialogView.findViewById(R.id.bt_End);
	     
	     
	     
	     
	     bt_Begin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Get Current Date
	            final Calendar c = Calendar.getInstance();
	            mYear = c.get(Calendar.YEAR);
	            mMonth = c.get(Calendar.MONTH);
	            mDay = c.get(Calendar.DAY_OF_MONTH);
	            DatePickerDialog datePickerDialog = new DatePickerDialog(Accueil.this, new DatePickerDialog.OnDateSetListener(){

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						bt_Begin.setText( year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
					}
	            	
	            }, mYear, mMonth, mDay);
	            datePickerDialog.show();
				
			}
		});
	     
	     
	     
	     bt_End.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Get Current Date
	            final Calendar c = Calendar.getInstance();
	            mYear = c.get(Calendar.YEAR);
	            mMonth = c.get(Calendar.MONTH);
	            mDay = c.get(Calendar.DAY_OF_MONTH);
	            DatePickerDialog datePickerDialog = new DatePickerDialog(Accueil.this, new DatePickerDialog.OnDateSetListener(){

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						bt_End.setText( year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
					}
	            	
	            }, mYear, mMonth, mDay);
	            datePickerDialog.show();
				
			}
		});
	     
	     
	        //On donne un titre à l'AlertDialog
	     adb.setTitle("Choose your period");
	    

	      //On modifie l'icône de l'AlertDialog pour le fun ;)
	        adb.setIcon(R.drawable.notifvodafone);
	        adb.setView(alertDialogView);
	        
	        
	        
	      //On affecte un bouton "OK" à notre AlertDialog et on lui affecte un évènement
	      adb.setPositiveButton("Search", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int which) {
	        	   createFile(); // Creation fichier
	        	   Intent intent = new Intent(Accueil.this, SliderTabPeriod.class);
	        	   intent.putExtra("DATEBEGIN", bt_Begin.getText().toString());
	        	   intent.putExtra("DATEEND", bt_End.getText().toString());
	        	   startActivityForResult(intent, RESULT_OK);
	        	   
	        	   
	          } });
	        
	      //On crée un bouton "Annuler" à notre AlertDialog et on lui affecte un évènement
	        adb.setNegativeButton("Back", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) {
	            	//Lorsque l'on cliquera sur annuler on quittera l'application
	            	
	          } });
	        adb.show();

	}

	private void createFile() {
		String fileNameStr="Nmc";
		String fileContentStr=bt_Begin.getText().toString() +"|" + bt_End.getText().toString();
		try {
		    
		    FileOutputStream fos= openFileOutput(fileNameStr, Context.MODE_PRIVATE);
		    // Open the writer
		    OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fos);
		    // Write
		    outputStreamWriter.write(fileContentStr);
		    // Close streams
		    outputStreamWriter.close();
		    fos.close();
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	@Override
	public Object onRetainNonConfigurationInstance() {
		// TODO Auto-generated method stub
		return task;
	}
	
	List<Network> getAll = new ArrayList<>();
	List<Network> get2GKPI = new ArrayList<>();
	
	Network_KPI network_KPI = new Network_KPI();
	Network_KPIWeekly network_KPIWeekly = new Network_KPIWeekly();
	Network_KPITwoWeekly network_KPITwoWeekly = new Network_KPITwoWeekly();
	
	private class AsyncCallWS extends AsyncTask<String, Void, Boolean> {
		private WeakReference<Accueil> mActivity = null;
		public AsyncCallWS(Accueil activityAccueil) {
			link(activityAccueil);
		}
		@Override
		protected Boolean doInBackground(String... params) {
			try {
				
				kpidao = new Network_KPIDAO(getBaseContext());
				kpidao.delete(getBaseContext());
				boolean res = kpidao.create(getBaseContext());
				return true;
			} catch (Exception e) {
				return false;
			}
			
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if (mActivity.get() != null) {
				if(result){
					Intent dailyPerform = new Intent(Accueil.this, SliderTab.class);
					startActivity(dailyPerform);
					loading.dismiss();
				} else {
					Toast.makeText(mActivity.get(), "Echec de recuperation", Toast.LENGTH_LONG).show();
				}
			}	
		}

		@Override
		protected void onPreExecute() {
			if(mActivity.get() != null){
			loading = new ProgressDialog(Accueil.this); 
            loading.setMessage("Checking..."); 
            loading.setTitle("Loading..."); 
            loading.show();
            
			}
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
		
		public void link (Accueil activityAccueil) {
			mActivity = new WeakReference<Accueil>(activityAccueil);
		}
	}
	
	
	
	
	
	
	
/*	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accueil, menu);
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
	
	*/
	
	
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
}
