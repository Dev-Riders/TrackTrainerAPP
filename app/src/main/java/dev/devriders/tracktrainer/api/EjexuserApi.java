package dev.devriders.tracktrainer.api;

import java.util.List;

import dev.devriders.tracktrainer.models.Ejercicio;
import dev.devriders.tracktrainer.models.Ejexuser;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EjexuserApi {

    @GET("/api/ejexuser/listar-ejexuser/{id}")
    Call<List<Ejexuser>> getAllEjerciciosByUsuarioId(@Path("id") int id);

    @POST("/api/ejexuser/crear-ejexuser/{id_usuario}/{id_ejercicio}")
    Call<Ejexuser> createComment(@Path("id_usuario") Long id_usuario,
                                 @Path("id_ejercicio") int id_ejercicio,
                                 @Body RequestBody requestBody
    );

    @PUT("/api/ejexuser/update-ejexuser/{id}")
    Call<Ejexuser> updateEjexuser(@Path("id") int id,
                                  @Body RequestBody requestBody
    );
}
