/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazUsuarioTipoServicio;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.TipoServicioVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import com.quickelp.programa.persistencia.vo.Usuario_TipoServicioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public class UsuarioTipoServicioDAO extends Conexion implements InterfazUsuarioTipoServicio {

    Conexion claseConexion = new Conexion();
    private PreparedStatement sentencia = null;
    private ResultSet resultado = null;
    private String sql = "";
    private List<Usuario_TipoServicioVO> lista = new ArrayList<>();
    private List<Usuario_TipoServicioVO> listaporTecnico = new ArrayList<>();

    @Override
    public List<Usuario_TipoServicioVO> listarTiposServ() {
        sql = "select idusutipo, nombre, apellido, nomTipoServicio from Usuario_tiposer inner join Usuario on usuario.idUsuario=Usuario_tiposer.idUsuario inner join tipoServicio on tiposervicio.idTiposer=Usuario_tiposer.idTiposer";

        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                UsuarioVO usuario = new UsuarioVO();
                TipoServicioVO tipoSer = new TipoServicioVO();
                Usuario_TipoServicioVO usuTipoServ = new Usuario_TipoServicioVO();
                usuTipoServ.setIdUsuTipo(resultado.getInt("idusutipo"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                tipoSer.setNomTipoServicio(resultado.getString("nomTipoServicio"));
                usuTipoServ.setIdUsuario(usuario);
                usuTipoServ.setIdTipoSer(tipoSer);
                lista.add(usuTipoServ);
            }
        } catch (Exception l) {
            System.err.print("Error al consultar los estados de servicio. " + l);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;
    }

    @Override
    public List<Usuario_TipoServicioVO> listarTiposServxId(int id) {
        sql = "select idusutipo, nombre, apellido, nomTipoServicio \n"
                + "from Usuario_tiposer \n"
                + "inner join Usuario on usuario.idUsuario=Usuario_tiposer.idUsuario \n"
                + "inner join tipoServicio on tiposervicio.idTiposer=Usuario_tiposer.idTiposer\n"
                + "where usuario.idUsuario="+id+" and usuario.idRol=2;";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                UsuarioVO usuario = new UsuarioVO();
                TipoServicioVO tipoSer = new TipoServicioVO();
                Usuario_TipoServicioVO usuTipoServ = new Usuario_TipoServicioVO();
                usuTipoServ.setIdUsuTipo(resultado.getInt("idusutipo"));
                usuario.setNombre(resultado.getString("nombre"));
                usuario.setApellido(resultado.getString("apellido"));
                tipoSer.setNomTipoServicio(resultado.getString("nomTipoServicio"));
                usuTipoServ.setIdUsuario(usuario);
                usuTipoServ.setIdTipoSer(tipoSer);
                listaporTecnico.add(usuTipoServ);
            }
        } catch (Exception l) {
            System.err.print("Error al consultar los estados de servicio. " + l);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listaporTecnico;
    }

    @Override
    public boolean agregarTipo(Usuario_TipoServicioVO usuTiposer) {
        boolean insertado;
        try {
            sql = "insert into Usuario_Tiposer(idUsuario, idTiposer) values(?,?);";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setObject(1, usuTiposer.getIdUsuario());
            sentencia.setObject(2, usuTiposer.getIdTipoSer());
            sentencia.executeUpdate();
            insertado = true;
        } catch (Exception i) {
            insertado = false;
            System.err.print("DAO Error al insertar datos en la tabla TipoServicios_Usuario. " + i);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    @Override
    public boolean eliminarTipo(int id) {
        boolean eliminado;
        try {
            sql = "delete from Usuario_Tiposer where idUsutipo=" + id;
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.executeUpdate();
            eliminado = true;
        } catch (Exception i) {
            eliminado = false;
            System.err.print("Error al eliminar datos en la id: " + id + " de la tabla TipoServicios_Usuario" + i);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return eliminado;
    }

}
