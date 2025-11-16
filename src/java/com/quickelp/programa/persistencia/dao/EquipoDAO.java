/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazRegistroPC;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.EquipoVO;
import com.quickelp.programa.persistencia.vo.MarcaVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class EquipoDAO extends Conexion implements InterfazRegistroPC {

    Conexion claseConexion = new Conexion();
    private PreparedStatement sentencia = null;
    private ResultSet resultado = null;
    private String sql = "";

    private List<EquipoVO> lista = new ArrayList<>();
    private List<EquipoVO> listaCliente = new ArrayList<>();

    /* ************************************************************************************************************************************************************************************************** */
 /* Consulta de Todos los Equipos registrados en General (Solo Administrador)*/
    @Override
    public List<EquipoVO> listadoEquipos() {
        //Consulta de MySQL
        sql = "select idequipo, tipoEquipo, nombreMarca, modelo, numSerial, numIdentidad, nombre, apellido, telefono from equipo inner join usuario on equipo.idusuario=usuario.idusuario inner join marca on equipo.idmarca=marca.idmarca;";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                UsuarioVO usuario = new UsuarioVO();
                equipo.setIdRegistro(resultado.getInt("idEquipo"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                equipo.setModelo(resultado.getString("modelo"));
                //marcaEquipo.setIdMarca(resultado.getInt("marca"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                //usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setNumeroIdentificacion(Long.parseLong(resultado.getString("numIdentidad")));
                usuario.setTelefono(Long.parseLong(resultado.getString("telefono")));
                equipo.setIdmarca(marcaEquipo);
                equipo.setIdUsuario(usuario);
                lista.add(equipo);
            }

        } catch (Exception l) {
            System.out.println("Error al consultar los equipos registrados en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;
    }

    /* ----------------------------Posible Revision -----------------------------*/
    @Override
    public List<EquipoVO> listadoxCliente(int id) {
        sql = "select idEquipo, tipoEquipo, nombreMarca, modelo, numSerial from equipo inner join marca on equipo.idmarca=marca.idmarca where idUsuario=" + id;
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                EquipoVO equipo=new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                equipo.setIdRegistro(resultado.getInt("idEquipo"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                equipo.setModelo(resultado.getString("modelo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setIdmarca(marcaEquipo);
                listaCliente.add(equipo);
            }
        } catch (Exception l) {
            System.out.println("Error al consultar los equipos registrados en la base de datos en el Rol Cliente. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listaCliente;
    }

    EquipoVO equipo = new EquipoVO();
    UsuarioVO usuario = new UsuarioVO();

    /* ************************************************************************************************************************************************************************************************** */
 /* Consulta de Todos los equipos del usuario que tengan la ID Usuario seleccionada */
 /* Aplica Administrador(Por Menu de Consulta) y Cliente mediante Lista*/
    @Override
    public EquipoVO consultarEquipos(int id) {
        //Consulta de MySQL
        sql = "select idEquipo, tipoEquipo, nombreMarca, modelo, numSerial, numIdentidad, nombre, apellido, telefono from equipo inner join usuario on equipo.idusuario=usuario.idusuario inner join marca on equipo.idmarca=marca.idmarca where idEquipo=?";
        EquipoVO equipo = new EquipoVO();
        MarcaVO marcaEquipo = new MarcaVO();
        UsuarioVO usuario = new UsuarioVO();
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                equipo.setIdRegistro(resultado.getInt("idEquipo"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                equipo.setModelo(resultado.getString("modelo"));
                //marcaEquipo.setIdMarca(resultado.getInt("idMarca"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                //usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                usuario.setNumeroIdentificacion(Long.parseLong(resultado.getString("numIdentidad")));
                usuario.setTelefono(Long.parseLong(resultado.getString("telefono")));
                equipo.setIdmarca(marcaEquipo);
                equipo.setIdUsuario(usuario);
            }
        } catch (Exception c) {
            System.err.print("DAO Error al consultar el equipo #: " + id + "seleccionado. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return equipo;
    }

    @Override
    public EquipoVO consultarxCliente(int id) {
        //Consulta de MySQL
        sql = "select idEquipo, tipoEquipo, nombreMarca, modelo, numSerial from equipo inner join marca on equipo.idmarca=marca.idmarca where idUsuario=" + id;
        EquipoVO equipo = new EquipoVO();
        MarcaVO marcaEquipo = new MarcaVO();
        UsuarioVO usuario = new UsuarioVO();
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                equipo.setIdRegistro(resultado.getInt("idEquipo"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                equipo.setModelo(resultado.getString("modelo"));
                //marcaEquipo.setIdMarca(resultado.getInt("idMarca"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                //usuario.setIdUsuario(resultado.getInt("idUsuario"));

                equipo.setIdmarca(marcaEquipo);
                equipo.setIdUsuario(usuario);
            }
        } catch (Exception c) {
            System.err.print("DAO Error al consultar el equipo #: " + id + "seleccionado. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return equipo;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /* Registro del Equipo del Cliente  (Administrador y Cliente)*/
    @Override
    public boolean AgregarEquipo(EquipoVO equipo) {
        boolean insertado = false;
        try {
            //Consulta de MySQL
            sql = "insert into equipo(tipoEquipo, idmarca, modelo, numSerial, idUsuario) values(?,?,?,?,?)";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Atributos que permito registrar
            sentencia.setObject(1, equipo.getTipoEquipo());
            sentencia.setObject(2, equipo.getIdmarca().getIdMarca());
            sentencia.setObject(3, equipo.getModelo());
            sentencia.setObject(4, equipo.getNumSerial());
            sentencia.setObject(5, equipo.getIdUsuario().getIdUsuario());
            sentencia.executeUpdate();
            insertado = true;
        } catch (Exception c) {
            insertado = false;
            System.err.print("DAO Error al insertar los datos del equipo en la base de datos. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    /* ************************************************************************************************************************************************************************************************** */
 /*  Eliminar un equipo registrado (Administrador y Cliente)*/
    @Override
    public boolean EliminarEquipo(int id) {
        boolean eliminado;
        try {
            sql = "delete from equipo where idEquipo=" + id;
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.executeUpdate();
            eliminado = true;
        } catch (Exception c) {
            eliminado = false;
            System.err.print("DAO Error al eliminar el equipo de la base de datos. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return eliminado;
    }
}
