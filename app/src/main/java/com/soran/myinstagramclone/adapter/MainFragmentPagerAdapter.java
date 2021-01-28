package com.soran.myinstagramclone.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.soran.myinstagramclone.view.HomeFragment;
import com.soran.myinstagramclone.view.LikesFragment;
import com.soran.myinstagramclone.view.ProfileFragment;
import com.soran.myinstagramclone.view.SerchFragment;

public class MainFragmentPagerAdapter extends FragmentStateAdapter {
    public MainFragmentPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SerchFragment();
            case 2:
                return new LikesFragment();
            case 3:
                return new ProfileFragment();
        }


        return null;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
