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
public class ReparacionVO {
    private Integer idReparacion;
    private Date fechaRecepcion;
    private Date fechaEntrega;
    private String solucion;
    private ServicioVO idServicio;

    public ReparacionVO() {
    }

    public ReparacionVO(Integer idReparacion, Date fechaRecepcion, Date fechaEntrega, String solucion, ServicioVO idServicio) {
        this.idReparacion = idReparacion;
        this.fechaRecepcion = fechaRecepcion;
        this.fechaEntrega = fechaEntrega;
        this.solucion = solucion;
        this.idServicio = idServicio;
    }

    public Integer getIdReparacion() {
        return idReparacion;
    }

    public void setIdReparacion(Integer idReparacion) {
        this.idReparacion = idReparacion;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public ServicioVO getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(ServicioVO idServicio) {
        this.idServicio = idServicio;
    }

    @Override
    public String toString() {
        return "ReparacionVO{" + "idReparacion=" + idReparacion + ", fechaRecepcion=" + fechaRecepcion + ", fechaEntrega=" + fechaEntrega + ", solucion=" + solucion + ", idServicio=" + idServicio + '}';
    }

    
}
