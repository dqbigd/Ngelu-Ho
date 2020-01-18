package com.example.ngeluho.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ngeluho.R;
import com.example.ngeluho.fragment.FutureDescFragment;
import com.example.ngeluho.fragment.PresentDescFragment;

public class DescriptionPagerAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public DescriptionPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new PresentDescFragment();
                break;
            case 1:
                fragment = new FutureDescFragment();
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
