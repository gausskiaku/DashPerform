package FragmentTwoWeekly;


import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapterTwoWeekly extends FragmentPagerAdapter {
	FragmentManager fragmentManager;

	public FragmentPageAdapterTwoWeekly(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new RevFragmentTwoWeekly();
		case 1:
			return new TwoGFragmentTwoWeekly();
		case 2:
			return new ThreeGFragmentTwoWeekly();
		case 3:
			return new RcsFragmentTwoWeekly();

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
