package dev.devriders.tracktrainer.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.EjercicioApi;
import dev.devriders.tracktrainer.api.MisionesApi;
import dev.devriders.tracktrainer.models.Ejercicio;
import dev.devriders.tracktrainer.models.Mision;
import dev.devriders.tracktrainer.utils.Constants;
import dev.devriders.tracktrainer.views.adapters.EjercicioAdapter;
import dev.devriders.tracktrainer.views.adapters.MisionAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MisionesTabFragment extends Fragment {

    private RecyclerView recyclerView;
    private MisionAdapter adapter;
  /*  public MisionesTabFragment() {// Required empty public constructor}*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_misiones_tab, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        fetchMisionesFromApi();

        return view;
    }

    private void fetchMisionesFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MisionesApi misionesApi = retrofit.create(MisionesApi.class);

        Call<List<Mision>> call = misionesApi.getAllMisiones();

        call.enqueue(new Callback<List<Mision>>() {
            @Override
            public void onResponse(Call<List<Mision>> call, Response<List<Mision>> response) {
                if(response.isSuccessful()) {
                    List<Mision> misionesList = response.body();
                    adapter = new MisionAdapter(getContext(), misionesList);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener ejercicios", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Mision>> call, Throwable t) {
                Toast.makeText(getContext(), "Fallo en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }


}