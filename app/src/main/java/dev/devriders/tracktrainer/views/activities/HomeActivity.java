package dev.devriders.tracktrainer.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.views.fragments.EjerciciosFragment;
import dev.devriders.tracktrainer.views.fragments.MisionesFragment;
import dev.devriders.tracktrainer.views.fragments.UsuarioFragment;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private EjerciciosFragment ejerciciosFragment;
    private MisionesFragment misionesFragment;
    private UsuarioFragment usuarioFragment;

    private static final int MENU_EJERCICIOS = R.id.nav_ejercicios;
    private static final int MENU_MISIONES = R.id.nav_misiones;
    private static final int MENU_PERFIL = R.id.nav_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        ejerciciosFragment = new EjerciciosFragment();
        misionesFragment = new MisionesFragment();
        usuarioFragment = new UsuarioFragment();

        // Mostrar el fragmento de ejercicios por defecto
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ejerciciosFragment)
                .commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == MENU_EJERCICIOS) {
                showEjerciciosFragment();
                return true;
            } else if (itemId == MENU_MISIONES) {
                showMisionesFragment();
                return true;
            } else if (itemId == MENU_PERFIL) {
                showUsuarioFragment();
                return true;
            }
            return false;
        });
    }

    private void showEjerciciosFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, ejerciciosFragment)
                .commit();
    }

    private void showMisionesFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, misionesFragment)
                .commit();
    }

    private void showUsuarioFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, usuarioFragment)
                .commit();
    }
}