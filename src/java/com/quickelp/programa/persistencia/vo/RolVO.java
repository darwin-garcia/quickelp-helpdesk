/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.vo;

/**
 *
 * @author Darwin Garcia
 */
public class RolVO {
    private Integer idRol;
    private String nombreRol;

    public RolVO() {
    }

    public RolVO(Integer idRol) {
        this.idRol = idRol;
    }
    
    public RolVO(Integer idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public String toString() {
        return "RolVO{" + "idRol=" + idRol + ", nombreRol=" + nombreRol + '}';
    }

    
    
}
