package com.f5.stockmeister;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Gopi on 24-Dec-15.
 */
public class adapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Portfolios", "Watchlist", "Account" };

    public adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return fragment_1.newInstance(0);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return fragment_2.newInstance(1);
            case 2: // Fragment # 1 - This will show SecondFragment
                return fragment_3.newInstance(2);
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
