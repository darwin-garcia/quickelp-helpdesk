/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.vo;

/**
 *
 * @author Felipe
 */
public class EquipoVO {
    private Integer idRegistro;
    private String tipoEquipo;
    private String numSerial;
    private String modelo;
    private MarcaVO idmarca;
    private UsuarioVO idUsuario;

    public EquipoVO() {
    }

    public EquipoVO(UsuarioVO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public EquipoVO(Integer idRegistro, String tipoEquipo, String numSerial, String modelo, MarcaVO idmarca, UsuarioVO idUsuario) {
        this.idRegistro = idRegistro;
        this.tipoEquipo = tipoEquipo;
        this.numSerial = numSerial;
        this.modelo = modelo;
        this.idmarca = idmarca;
        this.idUsuario = idUsuario;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(String tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getNumSerial() {
        return numSerial;
    }

    public void setNumSerial(String numSerial) {
        this.numSerial = numSerial;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public MarcaVO getIdmarca() {
        return idmarca;
    }

    public void setIdmarca(MarcaVO idmarca) {
        this.idmarca = idmarca;
    }

    public UsuarioVO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioVO idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "EquipoVO{" + "idRegistro=" + idRegistro + ", tipoEquipo=" + tipoEquipo + ", numSerial=" + numSerial + ", modelo=" + modelo + ", idmarca=" + idmarca + ", idUsuario=" + idUsuario + '}';
    }

    

    
    
}
