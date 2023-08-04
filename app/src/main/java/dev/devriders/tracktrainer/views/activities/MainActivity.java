package dev.devriders.tracktrainer.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dev.devriders.tracktrainer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Simula una carga, luego verifica si el usuario está logueado
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (usuarioEstaLogueado()) {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
                }
                finish();
            }
        }, 2000);
    }

    private boolean usuarioEstaLogueado() {
        return true;
    }
}