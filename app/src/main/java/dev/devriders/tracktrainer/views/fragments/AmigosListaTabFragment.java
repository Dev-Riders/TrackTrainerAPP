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
import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.AmigosApi;
import dev.devriders.tracktrainer.models.Amigo;
import dev.devriders.tracktrainer.models.Usuario;
import dev.devriders.tracktrainer.singleton.UserDataHolder;
import dev.devriders.tracktrainer.utils.Constants;
import dev.devriders.tracktrainer.views.adapters.AmigosAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AmigosListaTabFragment extends Fragment {
    private RecyclerView recyclerView;
    private AmigosAdapter adapter;
    private List<Amigo> listaAmigos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amigos_lista_tab, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewAmigos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AmigosAdapter(listaAmigos);
        recyclerView.setAdapter(adapter);

        cargarAmigos();
        return view;
    }

    private void cargarAmigos() {
        Usuario usuarioActual = UserDataHolder.getInstance().getCurrentUser();
        if (usuarioActual != null) {
            Long usuarioId = Long.valueOf(usuarioActual.getId());
            Log.d("AmigosListaTabFragment", "ID del usuario: " + usuarioId);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AmigosApi amigosApi = retrofit.create(AmigosApi.class);
            Call<List<Amigo>> call = amigosApi.getAllAmigosByUsuarioId(usuarioId);
            call.enqueue(new Callback<List<Amigo>>() {
                @Override
                public void onResponse(Call<List<Amigo>> call, Response<List<Amigo>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        listaAmigos.clear();
                        listaAmigos.addAll(response.body());
                        adapter.notifyDataSetChanged();

                        // Agregar un registro para ver los datos recibidos
                        Log.d("AmigosListaTabFragment", "Amigos recibidos: " + response.body());
                    } else {
                        // Agregar un registro en caso de respuesta no exitosa
                        Log.e("AmigosListaTabFragment", "Respuesta no exitosa: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<List<Amigo>> call, Throwable t) {
                    // Agregar un registro en caso de fallo
                    Log.e("AmigosListaTabFragment", "Error en la API: " + t.getMessage());
                }
            });
        } else {
            Log.e("AmigosListaTabFragment", "Usuario actual es nulo");
        }
    }


}