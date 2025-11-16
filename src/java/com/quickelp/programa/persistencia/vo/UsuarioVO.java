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
public class UsuarioVO {
    private Integer idUsuario;
    //TipoDocumentoVO
    private TipoDocumentoVO tipoIdentificacion;
    //RolVO
    private RolVO idRol;
    //Atributos de la tabla (Variables que se declaran)
    private Long numeroIdentificacion;
    private String nombre;
    private String apellido;    
    private Long telefono;
    private String correo;
    private String direccion;
    private String claveUsuario;
    private String confirmarClaveUsuario;

    /*Constructores*/
    public UsuarioVO() {
    }
    public UsuarioVO(String correo){
        this.correo = correo;
    }

    public UsuarioVO(String nombre, String correo) {
        this.nombre = nombre;
        this.correo = correo;
    }

    public UsuarioVO(Integer idUsuario, TipoDocumentoVO tipoIdentificacion, RolVO idRol, Long numeroIdentificacion, String nombre, String apellido, Long telefono, String correo, String direccion, String claveUsuario, String confirmarClaveUsuario) {
        this.idUsuario = idUsuario;
        this.tipoIdentificacion = tipoIdentificacion;
        this.idRol = idRol;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.claveUsuario = claveUsuario;
        this.confirmarClaveUsuario = confirmarClaveUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public TipoDocumentoVO getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoDocumentoVO tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public RolVO getIdRol() {
        return idRol;
    }

    public void setIdRol(RolVO idRol) {
        this.idRol = idRol;
    }

    public Long getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(Long numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }

    public String getConfirmarClaveUsuario() {
        return confirmarClaveUsuario;
    }

    public void setConfirmarClaveUsuario(String confirmarClaveUsuario) {
        this.confirmarClaveUsuario = confirmarClaveUsuario;
    }

    @Override
    public String toString() {
        return "UsuarioVO{" + "idUsuario=" + idUsuario + ", tipoIdentificacion=" + tipoIdentificacion + ", idRol=" + idRol + ", numeroIdentificacion=" + numeroIdentificacion + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", correo=" + correo + ", direccion=" + direccion + ", claveUsuario=" + claveUsuario + ", confirmarClaveUsuario=" + confirmarClaveUsuario + '}';
    }      
}
