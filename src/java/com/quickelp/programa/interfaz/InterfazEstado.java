/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;
import com.quickelp.programa.persistencia.vo.EstadoVO;
import java.util.List;
/**
 *
 * @author Darwin Garcia
 */
public interface InterfazEstado {
    public List listar();
    public abstract boolean agregarEstado(EstadoVO estado);
    public abstract boolean eliminarEstado(int id);
}
