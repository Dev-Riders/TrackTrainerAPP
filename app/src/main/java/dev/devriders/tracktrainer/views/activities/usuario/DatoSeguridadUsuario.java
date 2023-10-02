package dev.devriders.tracktrainer.views.activities.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.UsuarioApi;
import dev.devriders.tracktrainer.models.ResponseUsuario;
import dev.devriders.tracktrainer.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatoSeguridadUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato_seguridad_usuario);

        obtenerYMostrarCorreo();
    }

    private void obtenerYMostrarCorreo() {
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
                    // Si la respuesta es exitosa, mostrar el correo del usuario
                    String correo = response.body().getUsuario().getCorreo();
                    mostrarCorreo(correo);
                } else {
                    // Si hay un error, mostrar un mensaje de error
                    Toast.makeText(DatoSeguridadUsuario.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUsuario> call, Throwable t) {
                // Si hay un fallo en la llamada, mostrar un mensaje de error
                Toast.makeText(DatoSeguridadUsuario.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarCorreo(String correo) {
        TextInputEditText correoEditText = findViewById(R.id.correoEditText);
        correoEditText.setText(correo);
    }
}
