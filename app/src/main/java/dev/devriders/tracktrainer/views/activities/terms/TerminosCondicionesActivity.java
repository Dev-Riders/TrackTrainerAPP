package dev.devriders.tracktrainer.views.activities.terms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import dev.devriders.tracktrainer.R;

public class TerminosCondicionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos_condiciones);

        TextView termsTextView = findViewById(R.id.termsTextView);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Intent intent = new Intent(TerminosCondicionesActivity.this, PoliticaPrivacidadActivity.class);
                startActivity(intent);
            }
        };

        String termsContent = getString(R.string.terms_acceptance) + "\n\n" +
                getString(R.string.terms_registration) + "\n\n" +
                getString(R.string.terms_privacy) + "\n\n" +
                getString(R.string.terms_acceptable_use) + "\n\n" +
                getString(R.string.terms_intellectual_property) + "\n\n" +
                getString(R.string.terms_limitation) + "\n\n" +
                getString(R.string.terms_advertising) + "\n\n" +
                getString(R.string.terms_termination) + "\n\n" +
                getString(R.string.terms_changes) + "\n\n" +
                getString(R.string.terms_law) + "\n\n" +
                getString(R.string.terms_contact);

        SpannableString spannableString = new SpannableString(termsContent);
        int startIndex = termsContent.indexOf("Consultar");
        int endIndex = termsContent.indexOf("tu información.") + "tu información.".length();

        spannableString.setSpan(clickableSpan, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        termsTextView.setText(spannableString);
        termsTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}