package it.fmd.cocecl.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

import it.fmd.cocecl.fragments.CommunicationFragment;
import it.fmd.cocecl.fragments.DeliverylocFragment;
import it.fmd.cocecl.fragments.IncidentFragment;
import it.fmd.cocecl.fragments.MainstatusFragment;
import it.fmd.cocecl.fragments.MapFragment;
import it.fmd.cocecl.fragments.ReLoc_HomeFragment;

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
                MainstatusFragment tab1 = new MainstatusFragment();
                return tab1;
            case 1:
                IncidentFragment tab2 = new IncidentFragment();
                return tab2;
            case 2:
                DeliverylocFragment tab3 = new DeliverylocFragment();
                return tab3;
            case 3:
                MapFragment tab4 = new MapFragment();
                return tab4;
            case 4:
                CommunicationFragment tab5 = new CommunicationFragment();
                return tab5;
            case 5:
                ReLoc_HomeFragment tab6 = new ReLoc_HomeFragment();
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

/*
public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public final ArrayList<Fragment> screens2 = new ArrayList<Fragment>();


    private Context context;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public void removeType(String name){
        for(Fragment f: screens2){
            if(f.getClass().getName().contains(name)){ screens2.remove(f); return; }
        }
        this.notifyDataSetChanged();
    }

    public boolean addSt(String tag, Class<?> clss, Bundle args){
        if(clss==null) return false;
        if(!clss.getName().contains("St")) return false;
        if(!args.containsKey("cId")) return false;
        boolean has = false;
        boolean hasAlready = false;
        for(Fragment tab: screens2){
            if(tab.getClass().getName().contains("St")){
                has = true;
                if(tab.getArguments().containsKey("cId"))
                    if(tab.getArguments().getLong("cId") == args.getLong("cId")){
                        hasAlready = true;
                    }
                if(!hasAlready){
                    // exists but is different so replace
                    screens2.remove(tab);
                    this.addScreen(tag, clss, args, C.PAGE_ST);
                    // if returned true then it notifies dataset
                    return true;
                }
            }
            hasAlready = false;
        }

        if(!has){
            // no st yet exist in adapter
            this.addScreen(tag, clss, args, C.PAGE_ST);
            return true;
        }

        return false;
    }

    public boolean removeCCreate(){
        this.removeType("Create");
        return false;
    }

    @Override
    public int getItemPosition(Object object) {

        return POSITION_NONE; //To make notifyDataSetChanged() do something
    }

    public void addCCreate(){
        this.removeCCreate();
        Log.w("addding c", " ");
        this.addScreen("Create C",  CreateCFragment.class, null, C.PAGE_CREATE_C);
    }

    public void addScreen(String tag, Class<?> clss, Bundle args, int type){
        if(clss!=null){
            screens2.add(Fragment.instantiate(context, clss.getName(), args));
        }
    }

    @Override
    public int getCount() {
        return screens2.size();
    }


    @Override
    public Fragment getItem(int position) {
        return screens2.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (position >= getCount()) {
            FragmentManager manager = ((Fragment) object).getFragmentManager();
            FragmentTransaction trans = manager.beginTransaction();
            trans.remove((Fragment) object);
            trans.commit();
        }
    }


}
*/