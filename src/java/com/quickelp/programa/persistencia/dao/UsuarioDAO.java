/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfaceUsuario;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.RolVO;
import com.quickelp.programa.persistencia.vo.TipoDocumentoVO;
import com.quickelp.programa.persistencia.vo.UbicacionVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public class UsuarioDAO extends Conexion implements InterfaceUsuario {

    Conexion claseConexion = new Conexion();
    //private Connection conexion;
    private PreparedStatement sentencia = null;
    private ResultSet resultado = null;
    private String sql = "";
    //VO del Usuario
    UsuarioVO usuario = new UsuarioVO();
    //VO del Tipo de documento
    TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
    //VO del Rol
    RolVO rol = new RolVO();
    UbicacionVO ubicacion = new UbicacionVO();

    private List<UsuarioVO> lista = new ArrayList<>();

    @Override
    public List<UsuarioVO> listadoUsuarios() {
        sql = "select * from usuario inner join tipodocumento on usuario.idtipodocumento = tipodocumento.idtipodocumento inner join rol on usuario.idrol = rol.idrol";//
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //Id Usuario
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                //Tipo de Documento
                tipoDocumento.setIdTipoDocumento(resultado.getInt("idTipoDocumento"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                //Datos del Usuario
                usuario.setNumeroIdentificacion(resultado.getDouble("numeroIdentificacion"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setTelefono(resultado.getDouble("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));
                usuario.setClaveUsuario(resultado.getString("claveUsuario"));                
                //Rol
                rol.setIdRol(resultado.getInt("idRol"));
                rol.setNombreRol(resultado.getString("nombreRol"));                
                
                //Coloco la informacion de las otras tablas
                usuario.setTipoIdentificacion(tipoDocumento);
                usuario.setIdRol(rol);                
                
                //Instruccion de listar la informacion del usuario
                lista.add(usuario);
            }
        } catch (Exception l) {
            System.out.println("Error al consultar los usuarios en la base de datos. Excepcion: "+l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;

    }

    @Override
    public UsuarioVO consultaUsuario(int idUsuario) {
        try {
            sql = "select * from usuario where idUsuario=" + idUsuario;
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();

        } catch (Exception c) {
            System.err.print("Error al consultar los usuario seleccionado. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return usuario;
    }

    @Override
    public boolean AgregarUsuario(UsuarioVO usuario) {
        boolean insertado;
        try {
            sql = "insert into usuario(idTipoDocumento, numeroIdentificacion, idRol, nombre, apellido, telefono, correo, direccion, claveUsuario) values(?,?,?,?,?,?,?,?,?)";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setObject(1, usuario.getTipoIdentificacion().getIdTipoDocumento());//TipoIdentificacionVO
            sentencia.setObject(2, usuario.getNumeroIdentificacion());
            sentencia.setObject(3, usuario.getIdRol().getIdRol());//RolVO
            sentencia.setObject(4, usuario.getNombre());
            sentencia.setObject(5, usuario.getApellido());
            sentencia.setObject(6, usuario.getTelefono());
            sentencia.setObject(7, usuario.getCorreo());
            sentencia.setObject(8, usuario.getDireccion());
            sentencia.setObject(9, usuario.getClaveUsuario());

            sentencia.executeUpdate();
            insertado = true;
        } catch (Exception c) {
            insertado = false;
            System.err.print("Error al ingresar el/los usuarios en la base de datos. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    @Override
    public boolean ModificarUsuario(UsuarioVO usuario) {
        boolean modificado;
        try {
            sql = "update usuario set idrol=?, nombre=?, apellido=?, telefono=?, correo=?, direccion=?, claveUsuario=? where id=" + "?";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);

            sentencia.setObject(1, usuario.getIdRol().getIdRol());
            sentencia.setObject(2, usuario.getNombre());
            sentencia.setObject(3, usuario.getApellido());
            sentencia.setObject(4, usuario.getTelefono());
            sentencia.setObject(5, usuario.getCorreo());
            sentencia.setObject(6, usuario.getDireccion());
            sentencia.setObject(7, usuario.getClaveUsuario());
            sentencia.setObject(8, usuario.getIdUsuario());
            
            sentencia.executeUpdate();
            modificado = true;
        } catch (Exception c) {
            modificado = false;
            System.err.print("Error al modificar el usuario en la base de datos con la id. "+usuario.getIdUsuario()+". Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return modificado;
    }

    @Override
    public boolean EliminarUsuario(int id) {
        boolean eliminado;
        try {
            sql = "delete from usuario where idUsuario=" + id;
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.executeUpdate();
            eliminado = true;
        } catch (Exception c) {
            eliminado = false;
            System.err.print("Error al eliminar el usuario de la base de datos. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return eliminado;
    }

    @Override
    public boolean validarUsuario(UsuarioVO usuario) {
        boolean validado = false;
        sql = "select * from usuario where correo=? and claveUsuario=?";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Datos que solicita el login
            sentencia.setObject(1, usuario.getCorreo());
            sentencia.setObject(2, usuario.getClaveUsuario());
            //Ejecucion
            resultado = sentencia.executeQuery();      
            
            while(resultado.next()) {
                validado = true;
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setClaveUsuario(resultado.getString("claveUsuario"));                
            }

        } catch (Exception c) {
            validado=false;
            System.err.print("No es posible validar la informacion de usuario porque no hay conexion a la base de datos del usuario. Excepcion:" + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return validado;
    }

}
