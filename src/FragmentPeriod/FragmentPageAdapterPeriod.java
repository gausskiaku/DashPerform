package FragmentPeriod;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapterPeriod extends FragmentPagerAdapter {
	FragmentManager fragmentManager;

	public FragmentPageAdapterPeriod(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new RevFragmentPeriod();
		case 1:
			return new TwoGFragmentPeriod();
		case 2:
			return new ThreeGFragmentPeriod();
		case 3:
			return new RcsFragmentPeriod();

		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}
	
	

}
