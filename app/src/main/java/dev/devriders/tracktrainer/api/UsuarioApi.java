package dev.devriders.tracktrainer.api;

import dev.devriders.tracktrainer.models.ResponseUsuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioApi {
    @GET("/api/usuario/me")
    Call<ResponseUsuario> obtenerUsuario(@Header("Authorization") String authToken);
    @PUT("/usuario/{id}/suscripcion")
    Call<String> cambiarEstadoSuscripcion(@Path("id") Long id, @Body boolean suscrito);
}
