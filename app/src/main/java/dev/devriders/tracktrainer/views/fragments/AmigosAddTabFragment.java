package dev.devriders.tracktrainer.views.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.AmigosApi;
import dev.devriders.tracktrainer.models.Amigo;
import dev.devriders.tracktrainer.models.Usuario;
import dev.devriders.tracktrainer.singleton.UserDataHolder;
import dev.devriders.tracktrainer.utils.Constants;
import dev.devriders.tracktrainer.views.adapters.BusquedaUsuariosAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AmigosAddTabFragment extends Fragment {

    private EditText etBuscarUsuario;
    private Button btnBuscar;
    private RecyclerView recyclerViewResultados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_amigos_add_tab, container, false);

        etBuscarUsuario = view.findViewById(R.id.edittext_buscar_usuario);
        btnBuscar = view.findViewById(R.id.button_buscar_usuario);
        recyclerViewResultados = view.findViewById(R.id.recyclerview_resultados_busqueda);
        recyclerViewResultados.setLayoutManager(new LinearLayoutManager(getContext()));
        btnBuscar.setOnClickListener(v -> buscarUsuarios());

        return view;
    }

    private void buscarUsuarios() {
        String terminoBusqueda = etBuscarUsuario.getText().toString().trim();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmigosApi amigosApi = retrofit.create(AmigosApi.class);
        Call<List<Usuario>> call = amigosApi.buscarUsuarios(terminoBusqueda);
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    BusquedaUsuariosAdapter adapter = new BusquedaUsuariosAdapter(response.body(),
                            usuario -> {
                                enviarSolicitudAmistad(usuario);
                            });
                    recyclerViewResultados.setAdapter(adapter);
                } else {
                    Log.d("AmigosAddTabFragment", "Error al buscar usuarios"+ response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(getContext(), "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void enviarSolicitudAmistad(Long idUsuarioDestino) {
        Long idUsuarioActual = (long) UserDataHolder.getInstance().getCurrentUser().getId();

        Amigo solicitud = new Amigo();
        solicitud.setUsuario(new Usuario(idUsuarioActual));
        solicitud.setAmigo(new Usuario(idUsuarioDestino));
        solicitud.setEstado("aceptado");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AmigosApi amigosApi = retrofit.create(AmigosApi.class);
        Call<Amigo> call = amigosApi.enviarSolicitudAmistad(solicitud);
        call.enqueue(new Callback<Amigo>() {
            @Override
            public void onResponse(Call<Amigo> call, Response<Amigo> response) {
                if (response.isSuccessful()) {
                    // Solicitud enviada con éxito
                    Toast.makeText(getContext(), "Solicitud enviada", Toast.LENGTH_SHORT).show();
                } else {
                    // Manejo de error, respuesta no exitosa
                    Toast.makeText(getContext(), "Error al enviar solicitud", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Amigo> call, Throwable t) {
                // Manejo de error, fallo en la llamada a la API
                Toast.makeText(getContext(), "Fallo en la conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

