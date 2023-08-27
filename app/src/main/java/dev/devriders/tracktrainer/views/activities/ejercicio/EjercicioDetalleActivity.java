package dev.devriders.tracktrainer.views.activities.ejercicio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dev.devriders.tracktrainer.R;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EjercicioDetalleActivity extends AppCompatActivity {

    private ImageView exerciseImage;
    private TextView exerciseTitle, timerText;
    private EditText numericInput, repsInput;
    private Button decrementNumericButton, incrementNumericButton;
    private Button decrementRepsButton, incrementRepsButton;
    private Button startTimerButton, registerExerciseButton;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio_detalle);

        // Inicialización de las vistas
        exerciseImage = findViewById(R.id.exerciseImage);
        exerciseTitle = findViewById(R.id.exerciseTitle);
        timerText = findViewById(R.id.timerText);
        numericInput = findViewById(R.id.numericInput);
        repsInput = findViewById(R.id.repsInput);
        decrementNumericButton = findViewById(R.id.decrementNumericButton);
        incrementNumericButton = findViewById(R.id.incrementNumericButton);
        decrementRepsButton = findViewById(R.id.decrementRepsButton);
        incrementRepsButton = findViewById(R.id.incrementRepsButton);
        startTimerButton = findViewById(R.id.startTimerButton);
        registerExerciseButton = findViewById(R.id.registerExerciseButton);

        // Eventos de botones
        decrementNumericButton.setOnClickListener(v -> {
            double currentValue = Double.parseDouble(numericInput.getText().toString());
            if (currentValue > 0.5) {
                numericInput.setText(String.valueOf(currentValue - 0.5));
            }
        });

        incrementNumericButton.setOnClickListener(v -> {
            double currentValue = Double.parseDouble(numericInput.getText().toString());
            numericInput.setText(String.valueOf(currentValue + 0.5));
        });

        decrementRepsButton.setOnClickListener(v -> {
            int currentReps = Integer.parseInt(repsInput.getText().toString());
            if (currentReps > 1) {
                repsInput.setText(String.valueOf(currentReps - 1));
            }
        });

        incrementRepsButton.setOnClickListener(v -> {
            int currentReps = Integer.parseInt(repsInput.getText().toString());
            repsInput.setText(String.valueOf(currentReps + 1));
        });

        registerExerciseButton.setOnClickListener(v -> {
            numericInput.setText("0.5");
            repsInput.setText("1");
            // No reiniciamos el temporizador aquí. Se reiniciará cuando lo detengas o termine.
        });

        startTimerButton.setOnClickListener(v -> {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            countDownTimer = new CountDownTimer(45000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerText.setText(String.format(Locale.getDefault(), "%02d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                @Override
                public void onFinish() {
                    timerText.setText("00:45"); // Se reinicia a 45 segundos al terminar
                }
            }.start();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}