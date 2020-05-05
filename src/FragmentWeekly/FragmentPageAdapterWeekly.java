package FragmentWeekly;


import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapterWeekly extends FragmentPagerAdapter {
	FragmentManager fragmentManager;

	public FragmentPageAdapterWeekly(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new RevFragmentWeekly();
		case 1:
			return new TwoGFragmentWeekly();
		case 2:
			return new ThreeGFragmentWeekly();
		case 3:
			return new RcsFragmentWeekly();

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
