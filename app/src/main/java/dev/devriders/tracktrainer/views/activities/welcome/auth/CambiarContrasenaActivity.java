package dev.devriders.tracktrainer.views.activities.welcome.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dev.devriders.tracktrainer.R;

public class CambiarContrasenaActivity extends AppCompatActivity {

    private EditText editTextNuevaContrasena;
    private Button buttonCambiarContrasena;
    private String resetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);

        editTextNuevaContrasena = findViewById(R.id.editTextNuevaContrasena);
        buttonCambiarContrasena = findViewById(R.id.buttonCambiarContrasena);

        resetCode = getIntent().getStringExtra("resetCode");

        buttonCambiarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevaContrasena = editTextNuevaContrasena.getText().toString().trim();
                if (!nuevaContrasena.isEmpty()) {
                    cambiarContrasena(nuevaContrasena);
                } else {
                    Toast.makeText(CambiarContrasenaActivity.this, "Por favor, ingrese una nueva contraseña.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void cambiarContrasena(String nuevaContrasena) {
        String url = "http://10.0.2.2:25513/api/usuario/reset-password?code=" + resetCode;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(CambiarContrasenaActivity.this, "Contraseña cambiada exitosamente.", Toast.LENGTH_SHORT).show();

                        // Inicia la actividad LoginActivity
                        Intent intent = new Intent(CambiarContrasenaActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish(); // Cierra CambiarContrasenaActivity
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CambiarContrasenaActivity.this, "Error al cambiar la contraseña.", Toast.LENGTH_SHORT).show();
                        Log.d("Error", error.toString());
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() {
                try {
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("contraseña", nuevaContrasena);
                    return jsonBody.toString().getBytes("utf-8");
                } catch (Exception e) {
                    return null;
                }
            }
        };

        requestQueue.add(stringRequest);
    }
}