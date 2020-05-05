package FragmentMonthly;


import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapterMonthly extends FragmentPagerAdapter {
	FragmentManager fragmentManager;

	public FragmentPageAdapterMonthly(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new RevFragmentMonthly();
		case 1:
			return new TwoGFragmentMonthly();
		case 2:
			return new ThreeGFragmentMonthly();
		case 3:
			return new RcsFragmentMonthly();

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
