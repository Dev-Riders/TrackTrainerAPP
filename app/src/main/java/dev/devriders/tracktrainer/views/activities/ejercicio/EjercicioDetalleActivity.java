package dev.devriders.tracktrainer.views.activities.ejercicio;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import dev.devriders.tracktrainer.R;
import dev.devriders.tracktrainer.api.EjercicioApi;
import dev.devriders.tracktrainer.models.Ejercicio;
import dev.devriders.tracktrainer.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class EjercicioDetalleActivity extends AppCompatActivity {

    private ImageView exerciseImage;
    private TextView exerciseTitle, timerText, textViewDescripcion, videoTitle, datosNumericos, repeticiones;
    private EditText numericInput, repsInput;
    private Button decrementNumericButton, incrementNumericButton;
    private Button decrementRepsButton, incrementRepsButton;
    private Button startTimerButton, registerExerciseButton;
    private CountDownTimer countDownTimer;
    private int ejercicioId;
    //private int tipoCategoria;
    private String ejercicioCategoria;
    private String categoria;
    private VideoView exerciseVideo;
    private MediaController mediaController;

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
        textViewDescripcion = findViewById(R.id.textViewDescripcion);
        exerciseVideo = findViewById(R.id.exerciseVideo);
        mediaController = new MediaController(this);
        exerciseVideo.setMediaController(mediaController);
        videoTitle = findViewById(R.id.videoTitle);
        datosNumericos = findViewById(R.id.textViewDescripcionDistancia);
        repeticiones = findViewById(R.id.textViewDescripcionRepeticion);

        ejercicioId = getIntent().getIntExtra("idEjercicio", -1);
        if (ejercicioId != -1) {
            fetchEjercicioDetails(ejercicioId);
        }

        ejercicioCategoria = getIntent().getStringExtra("categorias");
        if (!ejercicioCategoria.equals("")) {
            fetchEjercicioDetails(ejercicioId);
        }

        // Eventos de botones
        if(ejercicioCategoria.equals("Fuerza")){
            numericInput.setVisibility(View.GONE);
            decrementNumericButton.setVisibility(View.GONE);
            incrementNumericButton.setVisibility(View.GONE);
            datosNumericos.setVisibility(View.GONE);
        }else {
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
        }
        if(ejercicioCategoria.equals("Cardio")||ejercicioCategoria.equals("Flexibilidad")||ejercicioCategoria.equals("Equilibrio")){
            repsInput.setVisibility(View.GONE);
            decrementRepsButton.setVisibility(View.GONE);
            incrementRepsButton.setVisibility(View.GONE);
            repeticiones.setVisibility(View.GONE);
        }else {
            decrementRepsButton.setOnClickListener(v -> {
                int currentReps = Integer.parseInt(repsInput.getText().toString());
                if (currentReps > 1) {
                    repsInput.setText(String.valueOf(currentReps - 2));
                }
            });

            incrementRepsButton.setOnClickListener(v -> {
                int currentReps = Integer.parseInt(repsInput.getText().toString());
                repsInput.setText(String.valueOf(currentReps + 1));
            });
        }
            registerExerciseButton.setOnClickListener(v -> {
                numericInput.setText("0.5");
                repsInput.setText("1");
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
                    timerText.setText("00:45");
                }
            }.start();
        });
    }

    private void fetchEjercicioDetails(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EjercicioApi ejercicioApi = retrofit.create(EjercicioApi.class);
        Call<Ejercicio> call = ejercicioApi.getEjercicioById(id);

        call.enqueue(new Callback<Ejercicio>() {
            @Override
            public void onResponse(Call<Ejercicio> call, Response<Ejercicio> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUIWithEjercicioDetails(response.body());
                }
            }

            @Override
            public void onFailure(Call<Ejercicio> call, Throwable t) {
                // Manejo de errores
            }
        });
    }

    private void updateUIWithEjercicioDetails(Ejercicio ejercicio) {
        exerciseTitle.setText(ejercicio.getNombreEjercicio());

        if (ejercicio.getImagenEjercicio() != null && !ejercicio.getImagenEjercicio().isEmpty()) {
            String imageUrl = Constants.BASE_URL + "/" + ejercicio.getImagenEjercicio();
            Glide.with(this)
                    .load(imageUrl)
                    .into(exerciseImage);
        } else {
            exerciseImage.setImageResource(R.drawable.backgroundwelcome); // Imagen por defecto
        }

        textViewDescripcion.setText(ejercicio.getDescripcionEjercicio());

        if (ejercicio.getVideoEjercicio() != null && !ejercicio.getVideoEjercicio().isEmpty()) {
            String videoUrl = Constants.BASE_URL + "/" + ejercicio.getVideoEjercicio().replace("\\", "/");
            Uri videoUri = Uri.parse(videoUrl);
            exerciseVideo.setVideoURI(videoUri);
            exerciseVideo.start();
            exerciseVideo.setVisibility(View.VISIBLE);
            videoTitle.setVisibility(View.VISIBLE);

            mediaController.setAnchorView(exerciseVideo);
        } else {
            exerciseVideo.setVisibility(View.GONE);
            videoTitle.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}