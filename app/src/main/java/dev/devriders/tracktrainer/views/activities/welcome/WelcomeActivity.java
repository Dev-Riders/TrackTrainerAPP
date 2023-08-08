package dev.devriders.tracktrainer.views.activities.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.views.activities.welcome.auth.LoginActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btnRegister = findViewById(R.id.buttonRegister);
        btnLogin = findViewById(R.id.buttonLogin);

        // Click listener para el botón de Registrarse
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes iniciar la actividad de registro, por ejemplo:
                // Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
                // startActivity(intent);
            }
        });

        // Click listener para el botón de Loguearse
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}