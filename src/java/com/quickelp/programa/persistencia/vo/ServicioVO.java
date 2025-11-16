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
public class ServicioVO {
    private Integer idServicio;
    private Date fechaSolicitud;
    private String descripcion;
    private UsuarioVO idUsuario;
    private EquipoVO idEquipo;
    private TipoServicioVO idTipoDelServ;
    private Usuario_TipoServicioVO idTipoSer;
    private EstadoVO idEstado;

    public ServicioVO() {
    }

    public ServicioVO(Integer idServicio, Date fechaSolicitud, String descripcion, UsuarioVO idUsuario, EquipoVO idEquipo, TipoServicioVO idTipoDelServ, Usuario_TipoServicioVO idTipoSer, EstadoVO idEstado) {
        this.idServicio = idServicio;
        this.fechaSolicitud = fechaSolicitud;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
        this.idEquipo = idEquipo;
        this.idTipoDelServ = idTipoDelServ;
        this.idTipoSer = idTipoSer;
        this.idEstado = idEstado;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UsuarioVO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioVO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public EquipoVO getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(EquipoVO idEquipo) {
        this.idEquipo = idEquipo;
    }

    public TipoServicioVO getIdTipoDelServ() {
        return idTipoDelServ;
    }

    public void setIdTipoDelServ(TipoServicioVO idTipoDelServ) {
        this.idTipoDelServ = idTipoDelServ;
    }

    public Usuario_TipoServicioVO getIdTipoSer() {
        return idTipoSer;
    }

    public void setIdTipoSer(Usuario_TipoServicioVO idTipoSer) {
        this.idTipoSer = idTipoSer;
    }

    public EstadoVO getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadoVO idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return "ServicioVO{" + "idServicio=" + idServicio + ", fechaSolicitud=" + fechaSolicitud + ", descripcion=" + descripcion + ", idUsuario=" + idUsuario + ", idEquipo=" + idEquipo + ", idTipoDelServ=" + idTipoDelServ + ", idTipoSer=" + idTipoSer + ", idEstado=" + idEstado + '}';
    }

    
}
