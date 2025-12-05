package com.sise.votoseguro.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.sise.votoseguro.data.api.EleccionVigenteApi;
import com.sise.votoseguro.data.api.RetrofitClient;
import com.sise.votoseguro.data.common.BaseResponse;
import com.sise.votoseguro.data.model.EleccionVigente;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EleccionVigenteRepository {
    private final EleccionVigenteApi eleccionVigenteApi;
    private final String TAG = EleccionVigenteRepository.class.getSimpleName();

    public EleccionVigenteRepository() {
        eleccionVigenteApi = RetrofitClient.getRetrofit().create(EleccionVigenteApi.class);
    }

    public LiveData<BaseResponse<List<EleccionVigente>>> listarEleccionesVigentes() {
        Log.i(TAG, "Iniciando peticion listarEleccionesVigentes");
        MutableLiveData<BaseResponse<List<EleccionVigente>>> data = new MutableLiveData<>();
        eleccionVigenteApi.listarEleccionesVigentes().enqueue(new Callback<BaseResponse<List<EleccionVigente>>>()  {
            @Override
            public void onResponse(Call<BaseResponse<List<EleccionVigente>>> call, Response<BaseResponse<List<EleccionVigente>>> response) {
                //Cuando la respuesta es satisfactoria, codigo http 200 o similar
                if(response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body());
                }

                //Cuando la respuesta es error, codigo http 400 o 500
                if(response.errorBody() != null) {
                    try {
                        String errorJson = response.errorBody().string();
                        Log.i(TAG, "errorJson: "+errorJson);
                    } catch (Exception e) {
                        Log.e(TAG, "Error al convertir respuesta error api: "+e.getMessage());
                    }
                    data.setValue(BaseResponse.error("El api devolvio un error"));
                }
            }

            //Cuando no hay conexion
            @Override
            public void onFailure(Call<BaseResponse<List<EleccionVigente>>> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
                data.setValue(BaseResponse.error("Fallo de conexión"));
            }
        });
        return data;
    }
}
