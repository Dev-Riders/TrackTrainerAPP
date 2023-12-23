package dev.devriders.tracktrainer.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.adapters.AmigosPagerAdapter;


public class AmigosFragment extends Fragment {

    private ViewPager2 viewPager;
    private AmigosPagerAdapter adapter;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amigos, container, false);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layoutamigos);
        adapter = new AmigosPagerAdapter(getActivity());
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Lista");
                            break;
                        case 1:
                            tab.setText("Añadir");
                            break;
                        //case 2:
                            //tab.setText("Solicitudes");
                            //break;
                    }
                }
        ).attach();

        return view;
    }
}
