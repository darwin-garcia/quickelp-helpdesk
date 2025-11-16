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
public class Usuario_TipoServicioVO {
    private Integer idUsuTipo;
    private UsuarioVO idUsuario;
    private TipoServicioVO idTipoSer;

    public Usuario_TipoServicioVO() {
    }

    public Usuario_TipoServicioVO(Integer idUsuTipo, UsuarioVO idUsuario, TipoServicioVO idTipoSer) {
        this.idUsuTipo = idUsuTipo;
        this.idUsuario = idUsuario;
        this.idTipoSer = idTipoSer;
    }

    public Integer getIdUsuTipo() {
        return idUsuTipo;
    }

    public void setIdUsuTipo(Integer idUsuTipo) {
        this.idUsuTipo = idUsuTipo;
    }

    public UsuarioVO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioVO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoServicioVO getIdTipoSer() {
        return idTipoSer;
    }

    public void setIdTipoSer(TipoServicioVO idTipoSer) {
        this.idTipoSer = idTipoSer;
    }

    @Override
    public String toString() {
        return "Usuario_TipoServicioVO{" + "idUsuTipo=" + idUsuTipo + ", idUsuario=" + idUsuario + ", idTipoSer=" + idTipoSer + '}';
    }
    
}
