package dev.devriders.tracktrainer.views.activities.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import dev.devriders.tracktrainer.R;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ListView optionsListView = findViewById(R.id.optionsListView);

        String[] options = {"Información personal", "Datos de seguridad o algo similar"};
        int[] optionIcons = {R.drawable.baseline_person_24_black, R.drawable.baseline_lock_person_black};

        IconListAdapter adapter = new IconListAdapter(this, options, optionIcons);
        optionsListView.setAdapter(adapter);

        optionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(PerfilActivity.this, DatosUsuarioActivity.class));
                        break;
                    case 1:
                        // Aquí iría la otra actividad que quieras abrir
                        break;
                }
            }
        });
    }
}