package com.sise.votoseguro.data.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sise.votoseguro.data.api.PersonaApi;
import com.sise.votoseguro.data.api.RetrofitClient;
import com.sise.votoseguro.data.common.BaseResponse;
import com.sise.votoseguro.data.model.Persona;
import com.sise.votoseguro.data.request.VerificarPersonaRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonaRepository {
    private final PersonaApi personaApi;

    public PersonaRepository() {
        personaApi = RetrofitClient.getRetrofit().create(PersonaApi.class);
    }

    public LiveData<BaseResponse<Persona>> verificarPersona(VerificarPersonaRequest request) {
        MutableLiveData<BaseResponse<Persona>> data = new MutableLiveData<>();
        personaApi.verificarPersona(request).enqueue(new Callback<BaseResponse<Persona>>() {
            @Override
            public void onResponse(Call<BaseResponse<Persona>> call, Response<BaseResponse<Persona>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<Persona>> call, Throwable throwable) {
                data.setValue(BaseResponse.error("Fallo de conexión: "+throwable.getMessage()));
            }
        });
        return data;
    }
}
