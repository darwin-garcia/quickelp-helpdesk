/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;

import com.quickelp.programa.persistencia.vo.EquipoVO;
import java.util.List;

/**
 * Tabla EquipoDAO
 * @author Darwin Garcia
 */
public interface InterfazRegistroPC {
    public List listadoEquipos();
    public List listadoxCliente(int id);
    public EquipoVO consultarEquipos(int id);
    public EquipoVO consultarxCliente(int id);
    public abstract boolean AgregarEquipo(EquipoVO equipos);
    public abstract boolean EliminarEquipo(int id);    
}
