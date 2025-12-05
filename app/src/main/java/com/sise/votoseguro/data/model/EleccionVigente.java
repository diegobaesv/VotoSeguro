package com.sise.votoseguro.data.model;

import java.util.Date;

import lombok.Data;

@Data
public class EleccionVigente {
    private int idEleccionVigente;
    private String descripcion;
    private String urlImagen;
    private Date fechaInicio;
    private Date fechaFin;
    private String estadoAuditoria;
    private Date fechaCreacion;
}
