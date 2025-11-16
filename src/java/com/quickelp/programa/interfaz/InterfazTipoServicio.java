/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;

import com.quickelp.programa.persistencia.vo.TipoServicioVO;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public interface InterfazTipoServicio {
    public List listar();
    public abstract boolean agregarTipo(TipoServicioVO tipoServicio);
    public abstract boolean eliminarTipo(int id);
}
