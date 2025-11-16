/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;

import com.quickelp.programa.persistencia.vo.ServicioVO;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public interface InterfazServicio {
    //**********************************************************************
    //* Metodos del ServicioDAO
    //**********************************************************************
    //Listado para el Administrador
    public List listadoServiciosActivosAdmin();
    public List listadoServiciosInactivosAdmin();
    public List listadoServiciosClientes();
    // Listado para el Tecnico
    public List buscarServiciosPendientes(int id);//Servicios del cliente pendiente por asignar
    public List buscarServiciosxIdCliente(int id);//Todos los servicios por ID Cliente
    public List buscarServiciosxIdTecnico(int id);//Todos los servicios asignados por ID Tecnico
    
    public ServicioVO consultarServicio(int idServicio);
    //Solicitud de Servicios (Solo Cliente)
    public abstract boolean AgregarServicios(ServicioVO servicio);
    public abstract boolean AgregarServiciosFecha(ServicioVO servicio);
    public abstract boolean AgregarServiciosAdmin(ServicioVO servicio);
    public abstract boolean AsignarServiciosTecnico(ServicioVO servicio);
    public abstract boolean ModificarServicios(ServicioVO servicio);
    public abstract boolean CancelarServicio(int id);  
}
