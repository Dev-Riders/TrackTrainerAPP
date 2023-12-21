package dev.devriders.tracktrainer.api;

import java.util.List;

import dev.devriders.tracktrainer.models.Amigo;
import dev.devriders.tracktrainer.models.Puntaje;
import dev.devriders.tracktrainer.models.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AmigosApi {
    @GET("/api/amistad/listar-amigos/{id}")
    Call<List<Amigo>> getAllAmigosByUsuarioId(@Path("id") Long id);
    @DELETE("/api/amistad/Delete-amigo-by-id/{id}")
    Call<Void> deleteAmigoById(@Path("id") Long id);

    @GET("/api/usuario/search/{nikname}")
    Call<List<Usuario>> buscarUsuarios(@Path("nikname") String terminoBusqueda);

    @POST("/api/amistad/enviar-solicitud")
    Call<Amigo> enviarSolicitudAmistad(@Body Amigo solicitud);

    @GET("/api/puntaje/{Idusuario}/get-puntaje-by-id-usuario")
    Call<List<Puntaje>> getPuntrajeByIdUsuario(@Path("Idusuario") Long idUsuario);
}
