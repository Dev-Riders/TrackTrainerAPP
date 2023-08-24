package dev.devriders.tracktrainer.views.fragments;

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
import dev.devriders.tracktrainer.views.activities.usuario.PerfilActivity;
import dev.devriders.tracktrainer.views.activities.welcome.WelcomeActivity;


public class UsuarioFragment extends Fragment {



    public UsuarioFragment() {
        // Required empty public constructor
    }

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        listView = view.findViewById(R.id.optionsListView);
        String[] options = {"Ver perfil", "Términos y condiciones", "Cerrar sesión o salir"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:

                        Intent intentProfile = new Intent(getContext(), PerfilActivity.class);
                        startActivity(intentProfile);
                        break;
                    case 1:
                        Intent intentTerms = new Intent(getContext(), TerminosCondicionesActivity.class);
                        startActivity(intentTerms);
                        break;
                    case 2:
                        cerrarSesionYSalir();
                        break;
                }
            }
        });

        return view;
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