package dev.devriders.tracktrainer.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dev.devriders.tracktrainer.views.fragments.MisionesTabFragment;
import dev.devriders.tracktrainer.views.fragments.RankingTabFragment;

public class MisionesPagerAdapter extends FragmentStateAdapter {

    public MisionesPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MisionesTabFragment();
            case 1:
                return new RankingTabFragment();
            default:
                return new MisionesTabFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;  // número de pestañas
    }
}
