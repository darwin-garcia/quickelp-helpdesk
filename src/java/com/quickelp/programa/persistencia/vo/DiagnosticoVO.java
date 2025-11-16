/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.vo;

import java.util.Date;

/**
 *
 * @author Felipe
 */
public class DiagnosticoVO {
    private Integer idDiagnostico;
    private Date fechaRecepcion;
    private String Solucion;
    private ServicioVO idServicio;

    public DiagnosticoVO() {
    }

    public DiagnosticoVO(Integer idDiagnostico, Date fechaRecepcion, String Solucion, ServicioVO idServicio) {
        this.idDiagnostico = idDiagnostico;
        this.fechaRecepcion = fechaRecepcion;
        this.Solucion = Solucion;
        this.idServicio = idServicio;
    }

    public Integer getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Integer idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getSolucion() {
        return Solucion;
    }

    public void setSolucion(String Solucion) {
        this.Solucion = Solucion;
    }

    public ServicioVO getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(ServicioVO idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public String toString() {
        return "DiagnosticoVO{" + "idDiagnostico=" + idDiagnostico + ", fechaRecepcion=" + fechaRecepcion + ", Solucion=" + Solucion + ", idServicio=" + idServicio + '}';
    }

    
}
