/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazEstado;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.EstadoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public class EstadoDAO extends Conexion implements InterfazEstado {

    Conexion claseConexion = new Conexion();
    private PreparedStatement sentencia = null;
    private ResultSet resultado = null;
    private String sql = "";
    private List<EstadoVO> lista = new ArrayList<>();

    @Override
    public List listar() {
        sql = "select * from Estado";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                EstadoVO estado = new EstadoVO();
                estado.setIdEstado(resultado.getInt("idEstado"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                lista.add(estado);
            }
        } catch (Exception l) {
            System.err.print("Error al consultar los estados de servicio. " + l);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;
    }

    @Override
    public boolean agregarEstado(EstadoVO estado) {
        boolean insertado;
        try {
            sql = "insert into Estado(nombreEstado) values(?)";
            sentencia = conexion.prepareStatement(sql);
            sentencia.setObject(1, estado.getNombreEstado());
            sentencia.executeUpdate();
            insertado = true;
        } catch (Exception i) {
            insertado = false;
            System.err.print("Error al insertar datos en la tabla Estado. " + i);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    @Override
    public boolean eliminarEstado(int id) {
        boolean eliminado;
        try {
            sql="delete from Estado where idEstado="+id;
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            sentencia.executeUpdate();
            eliminado=true;
        } catch (Exception i) {
            eliminado = false;
            System.err.print("Error al eliminar datos en la id: " + id + " de la tabla Estado" + i);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return eliminado;
    }

}
