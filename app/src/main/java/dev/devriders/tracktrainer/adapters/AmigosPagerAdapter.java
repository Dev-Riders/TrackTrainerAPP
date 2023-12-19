package dev.devriders.tracktrainer.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dev.devriders.tracktrainer.views.fragments.AmigosAddTabFragment;
import dev.devriders.tracktrainer.views.fragments.AmigosListaTabFragment;
import dev.devriders.tracktrainer.views.fragments.AmigosSolicitudTabFragment;

public class AmigosPagerAdapter extends FragmentStateAdapter {

    public AmigosPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new AmigosListaTabFragment();
            case 1:
                return new AmigosAddTabFragment();
            case 2:
                return new AmigosSolicitudTabFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Número total de pestañas
    }
}
