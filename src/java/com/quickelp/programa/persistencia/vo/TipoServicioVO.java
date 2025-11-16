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
public class TipoServicioVO {
    private Integer idTipoSer;
    private String nomTipoServicio;

    public TipoServicioVO() {
    }

    public TipoServicioVO(Integer idTipoSer, String nomTipoServicio) {
        this.idTipoSer = idTipoSer;
        this.nomTipoServicio = nomTipoServicio;
    }

    public Integer getIdTipoSer() {
        return idTipoSer;
    }

    public void setIdTipoSer(Integer idTipoSer) {
        this.idTipoSer = idTipoSer;
    }

    public String getNomTipoServicio() {
        return nomTipoServicio;
    }

    public void setNomTipoServicio(String nomTipoServicio) {
        this.nomTipoServicio = nomTipoServicio;
    }

    @Override
    public String toString() {
        return "TipoServicioVO{" + "idTipoSer=" + idTipoSer + ", nomTipoServicio=" + nomTipoServicio + '}';
    }

    
}
