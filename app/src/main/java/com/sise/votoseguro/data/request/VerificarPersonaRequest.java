package com.sise.votoseguro.data.request;

import java.util.Date;
import lombok.Data;

@Data
public class VerificarPersonaRequest {
    private String numeroDocumento;
    private Integer digitoVerificador;
    private Date fechaEmision;
}
