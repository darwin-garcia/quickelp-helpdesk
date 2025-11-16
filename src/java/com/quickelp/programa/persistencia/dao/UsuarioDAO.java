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

    //Llamo la clase Conexion
    Conexion claseConexion = new Conexion();
    //Llamo directamente la sentencia
    private PreparedStatement sentencia = null;
    //Llamo un Resultado de la base de datos
    private ResultSet resultado = null;
    private String sql = "";
    //Llamo una lista con informacion del UsuarioVO
    private List<UsuarioVO> lista = new ArrayList<>();
    private List<UsuarioVO> listaxRol = new ArrayList<>();

    /* ************************************************************************************************************************************************************************************************** */
 /* Consulta de Todos los Usuarios */
    @Override
    public List<UsuarioVO> listadoUsuarios() {
        //Consulta de MySQL
        sql = "select idUsuario, nombreTipoDoc, numIdentidad, nombre, apellido, telefono, correo, direccion, NombreRol from usuario inner join tipodoc on usuario.idtipodoc = tipodoc.idtipodoc inner join rol on usuario.idrol = rol.idrol";//
        try {
            //Obtengo la conexion
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            //Ciclo que permite listar la informacion a partir de la consulta
            while (resultado.next()) {
                UsuarioVO usuario = new UsuarioVO();
                //VO del Tipo de documento
                TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
                //VO del Rol
                RolVO rol = new RolVO();
                //Id Usuario
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                /*Tipo de Documento*/
                //tipoDocumento.setIdTipoDocumento(resultado.getInt("idTipoDocumento"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                //Datos del Usuario
                usuario.setNumeroIdentificacion(resultado.getLong("numIdentidad"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));
                //usuario.setClaveUsuario(resultado.getString("claveUsuario"));                
                /*Rol*/
                //rol.setIdRol(resultado.getInt("idRol"));
                rol.setNombreRol(resultado.getString("nombreRol"));

                //Coloco la informacion de las otras tablas
                usuario.setTipoIdentificacion(tipoDocumento);
                usuario.setIdRol(rol);

                //Instruccion de listar la informacion del usuario
                lista.add(usuario);
            }
        } catch (Exception l) {
            System.out.println("DAO Error al consultar los usuarios en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;

    }

    @Override
    public List<UsuarioVO> listaxRol(int idRol) {
        sql = "select u.idUsuario, u.idRol, r.nombreRol , td.nombreTipoDoc, u.numIdentidad, u.nombre, u.apellido, u.telefono, u.correo, u.direccion\n"
                + "from usuario u\n"
                + "inner join tipodoc td on td.idTipoDoc=u.idTipoDoc\n"
                + "inner join rol r on r.idRol=u.idRol\n"
                + "where u.idRol=" + idRol;
        try {
            //Obtengo la conexion
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            //Ciclo que permite listar la informacion a partir de la consulta
            while (resultado.next()) {
                UsuarioVO usuario = new UsuarioVO();
                //VO del Tipo de documento
                TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
                //VO del Rol
                RolVO rol = new RolVO();
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                rol.setIdRol(resultado.getInt("idRol"));
                rol.setNombreRol(resultado.getString("nombreRol"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                //Datos del Usuario
                usuario.setNumeroIdentificacion(resultado.getLong("numIdentidad"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));

                usuario.setTipoIdentificacion(tipoDocumento);
                usuario.setIdRol(rol);
                listaxRol.add(usuario);
            }
        } catch (Exception l) {
            System.out.println("DAO Error al consultar los usuarios en la base de datos por rol seleccionado. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listaxRol;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Consulta de un Usuario especifico desde la ID Usuario(cuando la idUsuario=1) */
    @Override
    public UsuarioVO consultaUsuario(int idUsuario) {
        //Consulta de MySQL
        sql = "select u.idUsuario, u.numIdentidad, u.nombre, u.apellido, u.telefono, u.correo, u.direccion, u.clave, u.idTipoDoc, t.nombreTipoDoc, u.idRol, r.NombreRol from usuario u inner join tipoDoc t on u.idTipoDoc=t.idtipodoc inner join Rol r on u.idRol=r.idRol where u.idUsuario=" + idUsuario;
        //VO del Usuario
        UsuarioVO usuario = new UsuarioVO();
        //VO del Tipo de documento
        TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
        //VO del Rol
        RolVO rol = new RolVO();
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            //Ciclo que permite listar la informacion a partir de la consulta
            while (resultado.next()) {
                //Obtener la ID
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                //Datos del Usuario
                usuario.setNumeroIdentificacion(resultado.getLong("numIdentidad"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));
                usuario.setClaveUsuario(resultado.getString("clave"));
                /*Tipo Documento*/
                tipoDocumento.setIdTipoDocumento(resultado.getInt("idTipoDoc"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                /*Rol*/
                rol.setIdRol(resultado.getInt("idRol"));
                rol.setNombreRol(resultado.getString("nombreRol"));

                //Coloco la informacion de las otras tablas
                usuario.setTipoIdentificacion(tipoDocumento);
                usuario.setIdRol(rol);
            }

        } catch (Exception c) {
            System.err.print("DAO Error al consultar el usuario con la id: " + idUsuario + " , seleccionado. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return usuario;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Registro de Usuarios (General) */
    @Override
    public boolean AgregarUsuario(UsuarioVO usuario) {
        boolean insertado;
        try {
            //Consulta de MySQL
            sql = "insert into usuario(idTipoDoc, numIdentidad, idRol, nombre, apellido, telefono, correo, direccion, clave) values(?,?,?,?,?,?,?,?,?)";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Atributos que permiten agregar la informacion de la tabla Usuario
            sentencia.setObject(1, usuario.getTipoIdentificacion().getIdTipoDocumento());//TipoIdentificacionVO
            sentencia.setObject(2, usuario.getNumeroIdentificacion());
            sentencia.setObject(3, usuario.getIdRol().getIdRol());//RolVO
            sentencia.setObject(4, usuario.getNombre());
            sentencia.setObject(5, usuario.getApellido());
            sentencia.setObject(6, usuario.getTelefono());
            sentencia.setObject(7, usuario.getCorreo());
            sentencia.setObject(8, usuario.getDireccion());
            sentencia.setObject(9, usuario.getClaveUsuario());
            //Ejecuto la consulta
            sentencia.executeUpdate();
            //Cuando todos los datos se agreguen de forma correcta envio una respuesta verdadera
            insertado = true;
        } catch (Exception c) {
            insertado = false;
            System.err.print("DAO Error al ingresar el/los usuarios en la base de datos. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Modificar la informacion de Usuario (Solo de la tabla Usuario) desde el ID Usuario seleccionado*/
    @Override
    public boolean ModificarUsuario(UsuarioVO usuario) {
        boolean modificado;
        try {
            //Consulta de MySQL
            sql = "update usuario set nombre=?, apellido=?, telefono=?, correo=?, direccion=? where idUsuario=?";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Atributos que permiten agregar la informacion de la tabla Usuario
            //sentencia.setObject(1, usuario.getIdRol().getIdRol());
            sentencia.setObject(1, usuario.getNombre());
            sentencia.setObject(2, usuario.getApellido());
            sentencia.setObject(3, usuario.getTelefono());
            sentencia.setObject(4, usuario.getCorreo());
            sentencia.setObject(5, usuario.getDireccion());
            sentencia.setObject(6, usuario.getIdUsuario());
            //Ejecuto la consulta
            sentencia.executeUpdate();
            //Cuando todos los datos se agreguen de forma correcta envio una respuesta verdadera
            modificado = true;
        } catch (Exception c) {
            modificado = false;
            System.err.print("DAO Error al modificar el usuario en la base de datos con la id. " + usuario.getIdUsuario() + ". Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return modificado;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Modificar la informacion de Usuario (Cliente o Tecnico)(Solo de la tabla Usuario) desde el ID Usuario seleccionado*/
    @Override
    public boolean ModificarUsuarioRegistrado(UsuarioVO usuario) {
        boolean modificado;
        try {
            //Consulta de MySQL
            sql = "update usuario set telefono=?, direccion=? where idUsuario=? and clave=?";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Atributos que permiten agregar la informacion de la tabla Usuario
            //sentencia.setObject(1, usuario.getIdRol().getIdRol());
            sentencia.setObject(1, usuario.getTelefono());
            sentencia.setObject(2, usuario.getDireccion());
            sentencia.setObject(3, usuario.getIdUsuario());
            sentencia.setObject(4, usuario.getClaveUsuario());
            //Ejecuto la consulta
            sentencia.executeUpdate();
            //Cuando todos los datos se agreguen de forma correcta envio una respuesta verdadera
            modificado = true;
        } catch (Exception c) {
            modificado = false;
            System.err.print("DAO Error al modificar el usuario en la base de datos con la id. " + usuario.getIdUsuario() + ". Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return modificado;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Eliminar un usuario registrado desde el ID Usuario */
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
            System.err.print("DAO Error al eliminar el usuario de la base de datos. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return eliminado;
    }

    @Override
    public boolean consultaporNumId(UsuarioVO usuario) {
        boolean consultado = false;
        try {
            sql = "select idUsuario, nombre, apellido, telefono, correo, direccion from Usuario where idTipoDoc=? and numIdentidad=?";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.setObject(1, usuario.getTipoIdentificacion().getIdTipoDocumento());
            sentencia.setObject(2, usuario.getNumeroIdentificacion());
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                consultado = true;
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
            }
        } catch (Exception c) {
            consultado = false;
            System.err.print("DAO Error al consultar la ID Usuario con los datos ingresados o no existe.: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return consultado;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Validar la Sesion del usuario (Comprueba el correo y la contraseña) */
    @Override
    public boolean validarUsuario(UsuarioVO usuario) {
        boolean validado = false;
        sql = "select idusuario, nombre, apellido, nombreRol, correo, clave from Usuario inner join rol on rol.idRol=usuario.idRol where correo=? and clave=?";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Datos que solicita el login
            sentencia.setObject(1, usuario.getCorreo());
            sentencia.setObject(2, usuario.getClaveUsuario());
            //Ejecucion
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                validado = true;
                RolVO rol = new RolVO();
                usuario.setIdUsuario(resultado.getInt("idusuario")); //Permite ver el ID usuario en la vista.
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                rol.setNombreRol(resultado.getString("nombreRol"));
                usuario.setIdRol(rol);
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setClaveUsuario(resultado.getString("clave"));
            }

        } catch (Exception c) {
            validado = false;
            System.err.print("No es posible validar la informacion de usuario porque no hay conexion a la base de datos del usuario. Excepcion:" + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return validado;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Cambiar la contraseña de Usuario en el menu Administrador */
    @Override
    public boolean cambiarPassword(UsuarioVO usuario) {
        boolean cambiado = false;
        sql = "update usuario set clave=? where correo=?";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.setObject(1, usuario.getClaveUsuario());
            sentencia.setObject(2, usuario.getCorreo());
            sentencia.executeUpdate();
            cambiado = true;
        } catch (Exception c) {
            cambiado = false;
            System.err.print("No se puede borrar la clave del usuario. Excepcion:" + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return cambiado;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Borrar la contraseña de Usuario cuando el Usuario no puede ingresar (guest o invitado) */
    @Override
    public boolean borrarPassword(UsuarioVO usuario) {
        boolean cambiado = false;
        sql = "update usuario set clave=? where idUsuario=?";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.setObject(1, usuario.getClaveUsuario());
            sentencia.setObject(2, usuario.getIdUsuario());
            sentencia.executeUpdate();
            cambiado = true;
        } catch (Exception c) {
            cambiado = false;
            System.err.print("No se puede borrar la clave del usuario. Excepcion:" + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return cambiado;
    }

}
