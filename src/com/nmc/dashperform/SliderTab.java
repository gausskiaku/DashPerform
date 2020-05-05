package com.nmc.dashperform;

import java.lang.ref.WeakReference;
import Dao.Network_KPIDAO;
import FragmentDaily.FragmentPageAdapter;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SliderTab extends FragmentActivity implements ActionBar.TabListener{

	ActionBar actionBar;
	ViewPager viewPager;
	FragmentPageAdapter ft;
	Network_KPIDAO kpidao;
	AsyncCallWS task = null;
	private static ProgressDialog loading;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slider_tab);
		viewPager = (ViewPager) findViewById(R.id.Pager);
		ft = new FragmentPageAdapter(getSupportFragmentManager());
		
		
		viewPager.setAdapter(ft);
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("Revenu").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("2G KPIs").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("3G KPIs").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Rcs").setTabListener(this));
		
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED));
		actionBar.setTitle("Daily Perform");
		
		
		
	
		//viewPager.
		//actionBar.setSelectedTabIndicatorColor(Color.YELLOW);
		
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				actionBar.setSelectedNavigationItem(arg0);
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		task = (AsyncCallWS) getLastNonConfigurationInstance();
		
		if(task != null){
			// On lie l'activité à l'AsyncTask
			task.link(this);
			}
	}
	@Override
	public Object onRetainCustomNonConfigurationInstance() {
		return task;
	};
	
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.slider_tab, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.refresh:
			AlertDialog.Builder a_builder = new AlertDialog.Builder(this);
			a_builder.setMessage("Do you want to refresh ?").setCancelable(false)
			.setNegativeButton("No", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			})
			.setPositiveButton("Yes", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					
				//	kpidao = new Network_KPIDAO(getBaseContext());
				//	kpidao.delete(getBaseContext());
				//	boolean res = kpidao.create(getBaseContext());
				//	Intent dailyPerform = new Intent(SliderTab.this, SliderTab.class);
				//	startActivity(dailyPerform);
				//	finish();
					
					
					
					task = new AsyncCallWS(SliderTab.this);
					task.execute(); 
					
				}
			});
			AlertDialog alert = a_builder.create();
			alert.setTitle("NMC DashPerform");
			alert.show();
			
			
			return true;

		default:
			break;
		}
		return false;
	}

	
	private class AsyncCallWS extends AsyncTask<String, Void, Boolean> {
		private WeakReference<SliderTab> mActivity = null;
		public AsyncCallWS(SliderTab sliderTab) {
			link(sliderTab);
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
					Intent dailyPerform = new Intent(SliderTab.this, SliderTab.class);
					startActivity(dailyPerform);
					finish();
					loading.dismiss();
				} else {
					Toast.makeText(mActivity.get(), "Echec de recuperation", Toast.LENGTH_LONG).show();
				}
			}	
		}

		@Override
		protected void onPreExecute() {
			if(mActivity.get() != null){
			loading = new ProgressDialog(SliderTab.this); 
            loading.setMessage("Update..."); 
            loading.setTitle("Loading..."); 
            loading.show();}
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
		
		public void link (SliderTab sliderTab) {
			mActivity = new WeakReference<SliderTab>(sliderTab);
		}
	}
}
