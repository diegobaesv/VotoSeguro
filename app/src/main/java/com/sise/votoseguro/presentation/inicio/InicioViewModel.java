package com.sise.votoseguro.presentation.inicio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sise.votoseguro.data.common.BaseResponse;
import com.sise.votoseguro.data.model.Persona;
import com.sise.votoseguro.data.repository.PersonaRepository;
import com.sise.votoseguro.data.request.VerificarPersonaRequest;

public class InicioViewModel extends ViewModel {

    private final PersonaRepository personaRepository;

    public InicioViewModel() {
        personaRepository = new PersonaRepository();
    }

    public LiveData<BaseResponse<Persona>> verificarPersona(VerificarPersonaRequest request) {
        return personaRepository.verificarPersona(request);
    }

}
