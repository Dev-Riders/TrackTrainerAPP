package dev.devriders.tracktrainer.api;


import dev.devriders.tracktrainer.models.Categoria;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CategoriaApi {
    @GET("/api/ejercicios/{id}/categorias")
    Call<Categoria> getAllCategoriasByEjercicioId(@Path("id") int id);
}
