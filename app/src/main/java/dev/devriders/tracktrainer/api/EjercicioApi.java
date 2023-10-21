package dev.devriders.tracktrainer.api;

import java.util.List;

import dev.devriders.tracktrainer.models.Ejercicio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EjercicioApi {
    @GET("/api/ejercicios/find-all-ejercicios")
    Call<List<Ejercicio>> getAllEjercicios();

    @GET("/api/ejercicios/{id}/id-ejercicio")
    Call<Ejercicio> getEjercicioById(@Path("id") int id);

}