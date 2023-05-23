/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;

import com.quickelp.programa.persistencia.vo.EstadoServicioVO;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public interface InterfazEstadoServicio {
    public List listar();
    public abstract boolean agregarEstado(EstadoServicioVO estadoServicio);
    public abstract boolean modificarEstado(EstadoServicioVO estadoServicio);
    public abstract boolean eliminarEstado(int id);
}
