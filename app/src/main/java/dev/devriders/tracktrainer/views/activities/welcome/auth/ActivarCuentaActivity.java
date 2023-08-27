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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.utils.Constants;

public class ActivarCuentaActivity extends AppCompatActivity {

    private EditText etCodigoValidacion;
    private Button btnEnviarCodigo;
    private RequestQueue requestQueue;
    private String url = Constants.BASE_URL+"/api/usuario/verify?code=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activar_cuenta);

        etCodigoValidacion = findViewById(R.id.et_codigoValidacion);
        btnEnviarCodigo = findViewById(R.id.btn_enviarCodigo);

        requestQueue = Volley.newRequestQueue(this);

        btnEnviarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = etCodigoValidacion.getText().toString().trim();

                // Construir la URL completa con el código
                String fullUrl = url + codigo;

                StringRequest stringRequest = new StringRequest(Request.Method.POST, fullUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("La cuenta ha sido verificada exitosamente")) {
                                    // Mostrar mensaje de éxito
                                    Toast.makeText(ActivarCuentaActivity.this, response, Toast.LENGTH_SHORT).show();

                                    // Redirigir a la actividad Home
                                    Intent intent = new Intent(ActivarCuentaActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish(); // Opcional, para finalizar esta actividad y evitar que se pueda volver atrás con el botón de retroceso
                                } else {
                                    // Mostrar mensaje de error
                                    Toast.makeText(ActivarCuentaActivity.this, response, Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Mostrar mensaje de error en la solicitud
                                Toast.makeText(ActivarCuentaActivity.this, "Error en la solicitud", Toast.LENGTH_SHORT).show();
                            }
                        });

                requestQueue.add(stringRequest);
            }
        });
    }
}
