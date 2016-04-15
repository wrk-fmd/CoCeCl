package it.fmd.cocecl.utilclass;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.fmd.cocecl.fragments.communicationFragment;
import it.fmd.cocecl.fragments.deliverylocFragment;
import it.fmd.cocecl.fragments.incidentFragment;
import it.fmd.cocecl.fragments.mainstatusFragment;
import it.fmd.cocecl.fragments.mapFragment;
import it.fmd.cocecl.fragments.reloc_homeFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public TabPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                mainstatusFragment tab1 = new mainstatusFragment();
                return tab1;
            case 1:
                incidentFragment tab2 = new incidentFragment();
                return tab2;
            case 2:
                deliverylocFragment tab3 = new deliverylocFragment();
                return tab3;
            case 3:
                mapFragment tab4 = new mapFragment();
                return tab4;
            case 4:
                communicationFragment tab5 = new communicationFragment();
                return tab5;
            case 5:
                reloc_homeFragment tab6 = new reloc_homeFragment();
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}