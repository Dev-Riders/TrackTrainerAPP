package dev.devriders.tracktrainer.views.activities.welcome.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.utils.Constants;
import dev.devriders.tracktrainer.views.activities.HomeActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextApellido, editTextNickname, editTextCorreo, editTextContraseña, editTextConfirmarContraseña;
    private Button botonRegistrar;
    private RequestQueue requestQueue;
    private String url = Constants.BASE_URL +"/api/usuario/register";

    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        editTextNombre = findViewById(R.id.et_Nombre);
        editTextApellido = findViewById(R.id.et_Apellido);
        editTextNickname = findViewById(R.id.et_Nickame);
        editTextCorreo = findViewById(R.id.et_Correo);
        editTextContraseña = findViewById(R.id.et_Contraseña);
        editTextConfirmarContraseña = findViewById(R.id.et_Confirma_Contraseña);

        botonRegistrar = findViewById(R.id.btn_Registro);

        requestQueue = Volley.newRequestQueue(this);

        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String nombre = editTextNombre.getText().toString().trim();
        String apellido = editTextApellido.getText().toString().trim();
        String nickname = editTextNickname.getText().toString().trim();
        String correo = editTextCorreo.getText().toString().trim();
        String contraseña = editTextContraseña.getText().toString().trim();
        String confirmarContraseña = editTextConfirmarContraseña.getText().toString().trim();

        // Verificar que todos los campos estén llenos
        if (nombre.isEmpty() || apellido.isEmpty() || nickname.isEmpty() || correo.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            Toast.makeText(RegistroActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar que los campos de contraseña sean iguales
        if (!contraseña.equals(confirmarContraseña)) {
            Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Resto del código para registrar al usuario
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nombre", nombre);
            jsonObject.put("apellido", apellido);
            jsonObject.put("nickname", nickname);
            jsonObject.put("correo", correo);
            jsonObject.put("contraseña", contraseña);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String password = editTextContraseña.getText().toString();
        String email = editTextCorreo.getText().toString();

        if (Pass_validation(password)) {
            if (Email_validation(email)) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String mensaje = response.getString("mensaje");
                                    Toast.makeText(RegistroActivity.this, mensaje, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                String errorMessage = "";
                                Toast.makeText(RegistroActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        });

                requestQueue.add(jsonObjectRequest);
                // Redirige a HomeActivity
                Intent intent = new Intent(RegistroActivity.this, ActivarCuentaActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(RegistroActivity.this, "Dirección de correo electrónico inválida", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegistroActivity.this, "Contraseña inválida", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean Pass_validation(String password) {
        // Validar una contraseña
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    private boolean Email_validation(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches(); // Usar la validación de Android
    }

}