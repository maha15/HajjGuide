package com.example.maheen.projectsmd;

/**
 * Created by maheen on 4/18/2017.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Prepare tab3 = new Prepare();
                return tab3;
            case 1:
                Umrah tab2 = new Umrah();
                return tab2;
            case 2:
                Hajj tab1 = new Hajj();
                return tab1;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
