package dev.devriders.tracktrainer.views.activities.usuario;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.UsuarioApi;
import dev.devriders.tracktrainer.models.InfoUsuario;
import dev.devriders.tracktrainer.models.ResponseUsuario;
import dev.devriders.tracktrainer.models.Usuario;
import dev.devriders.tracktrainer.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DatosUsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);  // Asume que tu archivo XML se llama activity_datos_usuario.xml

        // Inicialización de la lista desplegable de género
        inicializarListaDesplegableGenero();

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
                    // Si la respuesta es exitosa, mostrar los datos del usuario
                    ResponseUsuario responseUsuario = response.body();
                    mostrarDatosUsuario(responseUsuario);
                } else {
                    // Si hay un error, mostrar un mensaje de error
                    Toast.makeText(DatosUsuarioActivity.this, "Error: " + response.message(), Toast.LENGTH_LONG).show();
                    //muestra el error en la consola
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ResponseUsuario> call, Throwable t) {
                // Si hay un fallo en la llamada, mostrar un mensaje de error
                Toast.makeText(DatosUsuarioActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                //muestra el error en logcat
                System.out.println(t.getMessage());

            }
        });
    }

    private void inicializarListaDesplegableGenero() {
        // Array con las opciones de género
        String[] genderArray = new String[] {"Masculino", "Femenino"};

        // Creación de un ArrayAdapter con las opciones de género
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.dropdown_menu_popup_item, genderArray);

        // Obtención de la referencia al MaterialAutoCompleteTextView
        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.genderAutoCompleteTextView);

        // Establecimiento del ArrayAdapter en el MaterialAutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter);
    }

    private void mostrarDatosUsuario(ResponseUsuario responseUsuario) {
        // Obtener referencias a las vistas
        TextInputEditText nombreEditText = findViewById(R.id.nombreEditText);
        TextInputEditText apellidoEditText = findViewById(R.id.apellidoEditText);
        TextInputEditText nicknameEditText = findViewById(R.id.nicknameEditText);
        TextInputEditText fechaNacimientoEditText = findViewById(R.id.fechaNacimientoEditText);
        TextInputEditText pesoEditText = findViewById(R.id.pesoEditText);
        TextInputEditText estaturaEditText = findViewById(R.id.estaturaEditText);
        MaterialAutoCompleteTextView generoAutoCompleteTextView = findViewById(R.id.genderAutoCompleteTextView);

        // Establecer los valores de las vistas con los datos del usuario
        nombreEditText.setText(responseUsuario.getUsuario().getNombre());
        apellidoEditText.setText(responseUsuario.getUsuario().getApellido());
        nicknameEditText.setText(responseUsuario.getUsuario().getNickname());
        fechaNacimientoEditText.setText(responseUsuario.getInfoUsuario().getFechaNacimiento());
        pesoEditText.setText(String.valueOf(responseUsuario.getInfoUsuario().getPeso()));
        estaturaEditText.setText(String.valueOf(responseUsuario.getInfoUsuario().getEstatura()));
        generoAutoCompleteTextView.setText(responseUsuario.getInfoUsuario().getGenero(), false);
    }


}