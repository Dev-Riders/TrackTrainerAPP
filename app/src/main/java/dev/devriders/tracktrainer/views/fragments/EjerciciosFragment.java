package dev.devriders.tracktrainer.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.CategoriaApi;
import dev.devriders.tracktrainer.api.EjercicioApi;
import dev.devriders.tracktrainer.models.Categoria;
import dev.devriders.tracktrainer.models.Ejercicio;
import dev.devriders.tracktrainer.utils.Constants;
import dev.devriders.tracktrainer.views.adapters.EjercicioAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EjerciciosFragment extends Fragment {

    private RecyclerView recyclerView;
    private EjercicioAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ejercicios, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        fetchEjerciciosFromApi();

        return view;
    }

    private void fetchEjerciciosFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EjercicioApi ejercicioApi = retrofit.create(EjercicioApi.class);
        //CategoriaApi categoria = retrofit.create(CategoriaApi.class);
        Call<List<Ejercicio>> call = ejercicioApi.getAllEjercicios();

        call.enqueue(new Callback<List<Ejercicio>>() {
            @Override
            public void onResponse(Call<List<Ejercicio>> call, Response<List<Ejercicio>> response) {
                if(response.isSuccessful()) {
                    List<Ejercicio> ejerciciosList = response.body();

                    //Pruebas
                    if (ejerciciosList != null) {
                        for (Ejercicio ejercicio : ejerciciosList) {
                            // Obtén los datos necesarios para inicializar la Categoria
                            int idEjercicio = ejercicio.getIdEjercicio();
                            CategoriaApi categoria = retrofit.create(CategoriaApi.class);
                            Call<Categoria> callCategoria = categoria.getAllCategoriasByEjercicioId(idEjercicio);
                            callCategoria.enqueue(new Callback<Categoria>() {
                                @Override
                                public void onResponse(Call<Categoria> call, Response<Categoria> response) {
                                    if (response.isSuccessful()) {
                                        Categoria categoria = response.body();
                                        ejercicio.setCategorias(categoria);
                                    } else {
                                        Toast.makeText(getContext(), "Error al obtener ejercicios", Toast.LENGTH_SHORT).show();        // Manejar respuesta no exitosa para la segunda llamada
                                    }
                                }

                                @Override
                                public void onFailure(Call<Categoria> call, Throwable t) {
                                    // Manejar fallos en la segunda llamada
                                }
                            });

                        }
                    //Fin pruebas
                        adapter = new EjercicioAdapter(getContext(), ejerciciosList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Toast.makeText(getContext(), "Error al obtener ejercicios", Toast.LENGTH_SHORT).show();
                    }
                }

                /*@Override
                public void onFailure(Call<List<Ejercicio>> call, Throwable t) {
                    Toast.makeText(getContext(), "Fallo en la conexión", Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onFailure(Call<List<Ejercicio>> call, Throwable t) {

            }
    });
    }
}
