package dev.devriders.tracktrainer.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.AmigosApi;
import dev.devriders.tracktrainer.models.Amigo;
import dev.devriders.tracktrainer.models.Puntaje;
import dev.devriders.tracktrainer.models.Usuario;
import dev.devriders.tracktrainer.singleton.AmigosHolder;
import dev.devriders.tracktrainer.singleton.UserDataHolder;
import dev.devriders.tracktrainer.utils.Constants;
import dev.devriders.tracktrainer.views.adapters.RankingAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RankingTabFragment extends Fragment {
    private RecyclerView recyclerView;
    private RankingAdapter adapter;
    private List<Puntaje> listaPuntajes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking_tab, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewRanking);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        listaPuntajes = new ArrayList<>();
        adapter = new RankingAdapter(listaPuntajes);
        recyclerView.setAdapter(adapter);
        cargarPuntajesAmigos();
        return view;
    }

    private void cargarPuntajesAmigos() {
        List<Amigo> amigos = AmigosHolder.getInstance().getListaAmigos();
        if (amigos != null && !amigos.isEmpty()) {
            for (Amigo amigo : amigos) {
                cargarPuntajeAmigo(amigo.getAmigo().getId(), amigo);
            }
        }
    }

    private void cargarPuntajeAmigo(Long idAmigo, Amigo datosAmigo) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmigosApi amigosApi = retrofit.create(AmigosApi.class);
        Call<List<Puntaje>> callPuntaje = amigosApi.getPuntrajeByIdUsuario(idAmigo);
        callPuntaje.enqueue(new Callback<List<Puntaje>>() {
            @Override
            public void onResponse(Call<List<Puntaje>> call, Response<List<Puntaje>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Puntaje puntaje : response.body()) {
                        puntaje.setAmigo(datosAmigo); // Establecer el amigo en el puntaje
                        listaPuntajes.add(puntaje);
                    }
                    ordenarYPresentarPuntajes();
                }
            }

            @Override
            public void onFailure(Call<List<Puntaje>> call, Throwable t) {
                // Manejar fallos aquí
            }
        });
    }

    private void ordenarYPresentarPuntajes() {
        Collections.sort(listaPuntajes, new Comparator<Puntaje>() {
            @Override
            public int compare(Puntaje p1, Puntaje p2) {
                return p2.getPuntajeHistorico().compareTo(p1.getPuntajeHistorico());
            }
        });
        adapter.notifyDataSetChanged();
    }
}