package dev.devriders.tracktrainer.api;

import dev.devriders.tracktrainer.models.ResponseUsuario;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UsuarioApi {
    @GET("/api/usuario/me")
    Call<ResponseUsuario> obtenerUsuario(@Header("Authorization") String authToken);
}
