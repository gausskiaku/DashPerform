package com.nmc.dashperform;

import java.util.List;
import java.util.Vector;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

public class FragmentsSliderActivity extends FragmentActivity {

	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.viewpager);
		
		List fragments = new Vector();
		
		fragments.add(Fragment.instantiate(this, Rev_Traf.class.getName()));
		fragments.add(Fragment.instantiate(this, TwoG.class.getName()));
		fragments.add(Fragment.instantiate(this, ThreeG.class.getName()));
		fragments.add(Fragment.instantiate(this, Rcs.class.getName()));
		
		this.mPagerAdapter = (PagerAdapter) new MyPagerAdapter(super.getSupportFragmentManager(), fragments);
		ViewPager pager = (ViewPager) super.findViewById(R.id.viewpager);
		pager.setAdapter(this.mPagerAdapter);
		
	}
}
