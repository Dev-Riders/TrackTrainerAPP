package dev.devriders.tracktrainer.api;

import java.util.List;
import dev.devriders.tracktrainer.models.Mision;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MisionesApi {
    @GET("/api/mision/listar")
    Call<List<Mision>> getAllMisiones();
}
