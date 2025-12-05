package com.sise.votoseguro.data.api;

import com.sise.votoseguro.data.common.BaseResponse;
import com.sise.votoseguro.data.model.EleccionVigente;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface EleccionVigenteApi {
    @GET("elecciones-vigentes")
    Call<BaseResponse<List<EleccionVigente>>> listarEleccionesVigentes();
}
