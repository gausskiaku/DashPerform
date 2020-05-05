package com.nmc.dashperform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import FragmentPeriod.FragmentPageAdapterPeriod;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.webkit.DateSorter;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class SliderTabPeriod extends FragmentActivity implements ActionBar.TabListener{

	ActionBar actionBar;
	ViewPager viewPager;
	FragmentPageAdapterPeriod ft;
	String DATEBEGIN;
	String DATEEND;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slider_tab);
		viewPager = (ViewPager) findViewById(R.id.Pager);
		ft = new FragmentPageAdapterPeriod(getSupportFragmentManager());
		
		viewPager.setAdapter(ft);
		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("Revenu").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("2G KPIs").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("3G KPIs").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Rcs").setTabListener(this));
		
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.RED));
		
		actionBar.setTitle("Daily Perform Period");
		
		//viewPager.
		//actionBar.setSelectedTabIndicatorColor(Color.YELLOW);
		readfile(); // Lecture fichier
		
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
		tab.setIcon(new ColorDrawable(Color.RED));
		
		
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}	
	
	
	private void readfile() {
		String fileName = "Nmc";
		if (fileName != null) {
		    try {
		        //open the file and retrieve the inputStream
		        InputStream inputStream = openFileInput(fileName);
		        if (inputStream != null) {
		            // open a reader on the inputStream
		            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
		            
		            // String to use to store read lines
		            String str;
		            StringBuilder buf = new StringBuilder();
		            
		            // Read the file
		            while ((str = reader.readLine()) != null) {
		                buf.append(str + "\r\n");
		            }
		            // Close the reader
		            reader.close();
		            // Close the inputStream
		            inputStream.close();
		            // Affect the text to the textView
		            Toast.makeText(getBaseContext(), "Result : " + buf.toString(), Toast.LENGTH_LONG).show();
		        }
		    } catch (java.io.FileNotFoundException e) {
		        Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_LONG);
		    } catch (IOException e) {
		        Toast.makeText(this, "FileNotFoundException", Toast.LENGTH_LONG);
		    }
		}


	}
}
