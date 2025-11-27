package com.sise.votoseguro.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class Persona {
    private int idPersona;
    private String numeroDocumento;
    private Date fechaEmision;
    private int digitoVerificador;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String mesaVotacion;
    private boolean flagMiembroMesa;
    private Date fechaNacimiento;
    private String estadoAuditoria;
    private Date fechaCreacion;
}
