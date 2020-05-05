package com.nmc.dashperform;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyPagerAdapter extends FragmentPagerAdapter {

	private final List fragments;
	public MyPagerAdapter(FragmentManager fm, List fragments) {
		super(fm);
		this.fragments = fragments;
	}
	
	@Override
	public Fragment getItem(int position) {
		return (Fragment) this.fragments.get(position);
	}
	
	@Override
	public int getCount() {
		return this.fragments.size();
	}

	
}
