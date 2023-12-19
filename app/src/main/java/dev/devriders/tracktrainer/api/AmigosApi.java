package dev.devriders.tracktrainer.api;

import java.util.List;

import dev.devriders.tracktrainer.models.Amigo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AmigosApi {
    @GET("/api/amistad/listar-amigos/{id}")
    Call<List<Amigo>> getAllAmigosByUsuarioId(@Path("id") Long id);
}
