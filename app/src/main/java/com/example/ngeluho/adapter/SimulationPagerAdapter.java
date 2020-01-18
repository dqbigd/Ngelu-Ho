package com.example.ngeluho.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ngeluho.R;
import com.example.ngeluho.fragment.FutureCalcFragment;
import com.example.ngeluho.fragment.PresentCalcFragment;


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SimulationPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public SimulationPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PresentCalcFragment();
                break;
            case 1:
                fragment = new FutureCalcFragment();
                break;
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.capital_investment);
            case 1:
                return mContext.getResources().getString(R.string.investment);
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}