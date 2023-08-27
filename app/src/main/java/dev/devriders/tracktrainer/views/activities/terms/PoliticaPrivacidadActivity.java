package dev.devriders.tracktrainer.views.activities.terms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import dev.devriders.tracktrainer.R;

public class PoliticaPrivacidadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politica_privacidad);

        // Find the TextView in the layout
        TextView privacyTextView = findViewById(R.id.privacyTextView);

        // Set the text of the TextView to the privacy content
        privacyTextView.setText(
                getString(R.string.privacy_info) + "\n\n" +
                        getString(R.string.privacy_use) + "\n\n" +
                        getString(R.string.privacy_share) + "\n\n" +
                        getString(R.string.privacy_ads) + "\n\n" +
                        getString(R.string.privacy_security) + "\n\n" +
                        getString(R.string.privacy_access) + "\n\n" +
                        getString(R.string.privacy_links) + "\n\n" +
                        getString(R.string.privacy_changes) + "\n\n" +
                        getString(R.string.privacy_contact)
        );
    }
}