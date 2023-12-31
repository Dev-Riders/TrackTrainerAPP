package dev.devriders.tracktrainer.views.activities.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.UsuarioApi;
import dev.devriders.tracktrainer.models.ResponseUsuario;
import dev.devriders.tracktrainer.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ListView optionsListView = findViewById(R.id.optionsListView);

        String[] options = {"Información personal", "Datos de seguridad"};
        int[] optionIcons = {R.drawable.baseline_person_24_black, R.drawable.baseline_lock_person_black};

        IconListAdapter adapter = new IconListAdapter(this, options, optionIcons);
        optionsListView.setAdapter(adapter);

        optionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(PerfilActivity.this, DatosUsuarioActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(PerfilActivity.this, DatoSeguridadUsuario.class));
                        break;
                }
            }
        });

        obtenerYMostrarNickname();
    }

    private void obtenerYMostrarNickname() {
        // Obtención del token JWT
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String token = preferences.getString("token", "");

        // Creación de la instancia de Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Creación de la instancia de la interfaz UsuarioApi
        UsuarioApi usuarioApi = retrofit.create(UsuarioApi.class);

        // Llamada a la API para obtener los datos del usuario
        Call<ResponseUsuario> call = usuarioApi.obtenerUsuario(token);
        call.enqueue(new Callback<ResponseUsuario>() {
            @Override
            public void onResponse(Call<ResponseUsuario> call, Response<ResponseUsuario> response) {
                if (response.isSuccessful()) {
                    // Si la respuesta es exitosa, mostrar el nickname del usuario
                    String nickname = response.body().getUsuario().getNickname();
                    mostrarNickname(nickname);
                } else {
                    // Si hay un error, mostrar un mensaje de error
                    Toast.makeText(PerfilActivity.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUsuario> call, Throwable t) {
                // Si hay un fallo en la llamada, mostrar un mensaje de error
                Toast.makeText(PerfilActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarNickname(String nickname) {
        TextView nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(nickname);
    }
}
