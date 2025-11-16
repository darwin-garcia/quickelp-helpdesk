/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;

import com.quickelp.programa.persistencia.vo.ReparacionVO;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public interface InterfazReparaciones {
    public List listadoReparaciones();
    public List consultarReparaciones(int id);
    public abstract boolean AgregarReparaciones(ReparacionVO reparacion);
    public abstract boolean ModificarReparaciones(ReparacionVO reparacion);
}
