package dev.devriders.tracktrainer.views.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.views.activities.terms.TerminosCondicionesActivity;
import dev.devriders.tracktrainer.views.activities.usuario.IconListAdapter;
import dev.devriders.tracktrainer.views.activities.usuario.PerfilActivity;
import dev.devriders.tracktrainer.views.activities.welcome.WelcomeActivity;


public class UsuarioFragment extends Fragment {
    private ListView listView;
    private boolean isSuscrito; // Estado de suscripción del usuario

    public UsuarioFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        listView = view.findViewById(R.id.optionsListView);
        actualizarListaOpciones();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intentProfile = new Intent(getContext(), PerfilActivity.class);
                        startActivity(intentProfile);
                        break;
                    case 1:
                        manejarSuscripcion();
                        break;
                    case 2:
                        Intent intentTerms = new Intent(getContext(), TerminosCondicionesActivity.class);
                        startActivity(intentTerms);
                        break;
                    case 3:
                        cerrarSesionYSalir();
                        break;
                }
            }
        });

        return view;
    }

    private void actualizarListaOpciones() {
        isSuscrito = obtenerEstadoSuscripcion(); // Obtén el estado de suscripción del usuario

        String[] options;
        int[] icons;
        if (isSuscrito) {
            options = new String[]{"Ver perfil", "Cancelar suscripción", "Términos y condiciones", "Cerrar sesión o salir"};
            icons = new int[]{R.drawable.baseline_person_24_black, R.drawable.baseline_cancel_black, R.drawable.baseline_description_black, R.drawable.baseline_logout_black};
        } else {
            options = new String[]{"Ver perfil", "Suscribirse ahora", "Términos y condiciones", "Cerrar sesión o salir"};
            icons = new int[]{R.drawable.baseline_person_24_black, R.drawable.baseline_attach_money_black, R.drawable.baseline_description_black, R.drawable.baseline_logout_black};
        }

        IconListAdapter adapter = new IconListAdapter(getContext(), options, icons);
        listView.setAdapter(adapter);
    }

    private boolean obtenerEstadoSuscripcion() {
        return false;
    }

    private void manejarSuscripcion() {
        if (isSuscrito) {
            mostrarDialogoConfirmacion("Cancelar suscripción", "¿Estás seguro de que deseas cancelar tu suscripción?");
        } else {
            mostrarDialogoConfirmacion("Suscribirse ahora", "¿Deseas suscribirte por 1.990 CLP?");
        }
    }

    private void mostrarDialogoConfirmacion(String titulo, String mensaje) {
        new AlertDialog.Builder(getContext())
                .setTitle(titulo)
                .setMessage(mensaje)
                .setPositiveButton("Confirmar", (dialog, which) -> {
                    if (isSuscrito) {
                        cancelarSuscripcionUsuario();
                    } else {
                        suscribirUsuario();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void suscribirUsuario() {
        isSuscrito = true;
        actualizarListaOpciones();
    }

    private void cancelarSuscripcionUsuario() {
        isSuscrito = false;
        actualizarListaOpciones();
    }

    private void cerrarSesionYSalir() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("token");
        editor.apply();
        Intent intent = new Intent(getContext(), WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        getActivity().finish();
    }
}