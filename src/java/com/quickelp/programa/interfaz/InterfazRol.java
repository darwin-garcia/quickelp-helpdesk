/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;
import com.quickelp.programa.persistencia.vo.RolVO;
import java.util.List;
/**
 *
 * @author Darwin Garcia
 */
public interface InterfazRol {
    public List listaRoles();
    public RolVO ConsultarRol(int id);
    public abstract boolean AgregarRoles(RolVO rol);
    public abstract boolean ModificarRoles(RolVO rol);
    public abstract boolean EliminarRoles(int id);   
}
