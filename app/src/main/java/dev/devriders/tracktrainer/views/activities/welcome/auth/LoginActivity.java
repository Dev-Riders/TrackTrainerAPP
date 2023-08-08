package dev.devriders.tracktrainer.views.activities.welcome.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.views.activities.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText etCorreo;
    private EditText etContraseña;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etCorreo = findViewById(R.id.editTextEmail);
        etContraseña = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.buttonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = etCorreo.getText().toString().trim();
                String contraseña = etContraseña.getText().toString().trim();

                if (correo.isEmpty() || contraseña.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Ingresa correo y contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    performLogin(correo, contraseña);
                }
            }
        });
    }

    private void performLogin(String correo, String contraseña) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("correo", correo);
            jsonObject.put("contraseña", contraseña);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        new LoginTask().execute(jsonObject.toString());
    }

    private class LoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String jsonInput = params[0];
            String response = "";

            try {
                URL url = new URL("http://10.0.2.2:25513/api/usuario/login");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                os.write(jsonInput.getBytes());
                os.flush();
                os.close();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    response += line;
                }
                br.close();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("token", response);
                editor.apply();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.d(TAG, "Login response: " + result);

            if (!result.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Error de login", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
