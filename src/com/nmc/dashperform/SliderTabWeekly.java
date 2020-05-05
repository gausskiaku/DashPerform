package com.nmc.dashperform;

import FragmentWeekly.FragmentPageAdapterWeekly;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

@SuppressWarnings("deprecation")
public class SliderTabWeekly extends FragmentActivity implements ActionBar.TabListener{

	ActionBar actionBar;
	ViewPager viewPager;
	FragmentPageAdapterWeekly ft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slider_tab);
		viewPager = (ViewPager) findViewById(R.id.Pager);
		ft = new FragmentPageAdapterWeekly(getSupportFragmentManager());
		
		viewPager.setAdapter(ft);
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("Revenu").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("2G KPIs").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("3G KPIs").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Rcs").setTabListener(this));
		
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED));
		actionBar.setTitle("Daily Perform Weekly");
		
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
	}
	
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
	
	

	
}
