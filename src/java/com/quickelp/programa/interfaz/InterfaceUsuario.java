/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;

import com.quickelp.programa.persistencia.vo.UsuarioVO;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public interface InterfaceUsuario {
    public List listadoUsuarios();
    public UsuarioVO consultaUsuario(int id);
    public abstract boolean AgregarUsuario(UsuarioVO usuario);
    public abstract boolean ModificarUsuario(UsuarioVO usuario);
    public abstract boolean EliminarUsuario(int id);
    public abstract boolean validarUsuario(UsuarioVO usuario);
}
