package dev.devriders.tracktrainer.views.activities.welcome.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.utils.Constants;

public class RecuperacionActivity extends AppCompatActivity {

    private TextInputLayout textInputEmailRecuperacion;
    private com.google.android.material.button.MaterialButton buttonSolicitarCodigo;
    private TextInputLayout textInputCodigo;
    private com.google.android.material.button.MaterialButton buttonEnviarCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperacion);

        textInputEmailRecuperacion = findViewById(R.id.textInputEmailRecuperacion);
        buttonSolicitarCodigo = findViewById(R.id.buttonSolicitarCodigo);
        textInputCodigo = findViewById(R.id.textInputCodigo);
        buttonEnviarCodigo = findViewById(R.id.buttonEnviarCodigo);

        buttonSolicitarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSolicitarCodigo.setEnabled(false);  // Desactivar el botón

                String correo = textInputEmailRecuperacion.getEditText().getText().toString().trim();
                if (Email_validation(correo)) {
                    enviarSolicitudRecuperacion(correo);
                } else {
                    Toast.makeText(RecuperacionActivity.this, "Por favor, ingrese un correo válido.", Toast.LENGTH_SHORT).show();
                    buttonSolicitarCodigo.setEnabled(true);  // Re-activar el botón si el correo no es válido
                }
            }
        });

        buttonEnviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = textInputCodigo.getEditText().getText().toString().trim();
                if (!codigo.isEmpty()) {
                    Intent intent = new Intent(RecuperacionActivity.this, CambiarContrasenaActivity.class);
                    intent.putExtra("resetCode", codigo);
                    startActivity(intent);
                } else {
                    Toast.makeText(RecuperacionActivity.this, "Por favor, ingrese el código.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean Email_validation(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void enviarSolicitudRecuperacion(String correo) {
        String url = Constants.BASE_URL +"/api/usuario/forgot-password";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        textInputCodigo.setVisibility(View.VISIBLE);
                        buttonEnviarCodigo.setVisibility(View.VISIBLE);
                        Toast.makeText(RecuperacionActivity.this, "Se ha enviado un código a tu correo.", Toast.LENGTH_SHORT).show();
                        buttonSolicitarCodigo.setEnabled(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RecuperacionActivity.this, "Error al solicitar el código. Verifique su correo e inténtelo de nuevo.", Toast.LENGTH_SHORT).show();
                        buttonSolicitarCodigo.setEnabled(true);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", correo);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0, // Tiempo de espera hasta el próximo intento
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, // Intentos máximos
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)); // Factor de multiplicación para el tiempo de espera

        requestQueue.add(stringRequest);
    }
}