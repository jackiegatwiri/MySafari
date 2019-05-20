package com.jacky.mysafari.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.jacky.mysafari.DestinationDetailFragment;
import com.jacky.mysafari.Models.Destination;

import java.util.ArrayList;

public class DestinitionPagerAdapter  extends FragmentPagerAdapter {
    private ArrayList<Destination> mdestinitions;

    public DestinitionPagerAdapter(FragmentManager fm, ArrayList<Destination> destinations) {
        super(fm);
        mdestinitions = destinations;
    }

    @Override
    public Fragment getItem(int position) {
        return DestinationDetailFragment.newInstance(mdestinitions.get(position));
    }

    @Override
    public int getCount() {
        return mdestinitions.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mdestinitions.get(position).getmName();
    }
}
