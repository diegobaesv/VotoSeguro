package com.sise.votoseguro.data.api;

import com.sise.votoseguro.data.common.BaseResponse;
import com.sise.votoseguro.data.model.Persona;
import com.sise.votoseguro.data.request.ValidarPersonaRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PersonaApi {
    @POST("personas/validar")
    Call<BaseResponse<Persona>> verificarPersona(@Body ValidarPersonaRequest request);
}
