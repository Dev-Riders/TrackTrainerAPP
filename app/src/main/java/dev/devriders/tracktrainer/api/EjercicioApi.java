package dev.devriders.tracktrainer.api;

import java.util.List;

import dev.devriders.tracktrainer.models.Ejercicio;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EjercicioApi {
    @GET("/api/ejercicio/findAllEjercicios")
    Call<List<Ejercicio>> getAllEjercicios();
}