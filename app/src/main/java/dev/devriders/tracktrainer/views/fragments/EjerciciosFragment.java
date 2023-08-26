package dev.devriders.tracktrainer.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.views.adapters.EjercicioAdapter;

public class EjerciciosFragment extends Fragment {

    private RecyclerView recyclerView;
    private EjercicioAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        // Datos genéricos
        List<String> ejercicios = Arrays.asList("Ejercicio 1", "Ejercicio 2", "Ejercicio 3", "Ejercicio 4");
        adapter = new EjercicioAdapter(getContext(), ejercicios);
        recyclerView.setAdapter(adapter);

        return view;
    }
}

