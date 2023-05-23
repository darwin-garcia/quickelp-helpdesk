/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;
import com.quickelp.programa.interfaz.InterfazEstadoServicio;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.EstadoServicioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Darwin Garcia
 */
public class EstadoServicioDAO extends Conexion implements InterfazEstadoServicio{
    Conexion claseConexion = new Conexion();
    //private Connection conexion;
    private PreparedStatement sentencia=null;
    private ResultSet resultado=null;
    private String sql="";
    private List<EstadoServicioVO> lista = new ArrayList<>();
    
    @Override
    public List<EstadoServicioVO> listar() {
        sql="select * from estadoservicio";
        try{
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                EstadoServicioVO estadoServicio = new EstadoServicioVO(resultado.getInt("idEstadoServicio"));
                estadoServicio.setNombreEstadoServicio(resultado.getString("nombreEstadoServicio"));
                lista.add(estadoServicio);
            }
        }catch(Exception l){
            System.err.print("Error al consultar la tabla de estadoServicio. "+l);
        }finally{
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;
    }

    @Override
    public boolean agregarEstado(EstadoServicioVO estadoServicio) {
        boolean insertado=true;
        try{
            sql="insert into estadoservicio(idEstadoServicio, nombreEstadoServicio) values(?,?)";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            
            sentencia.setObject(1, estadoServicio.getIdEstadoServicio());
            sentencia.setObject(2, estadoServicio.getNombreEstadoServicio());
            
            sentencia.executeUpdate();
            insertado=true;
        }
        catch(Exception i){
            insertado=false;
            System.err.print("Error al insertar datos en los roles. "+i);
        }
        finally{
            Conexion.cerrar(sentencia,resultado);
        }
        return insertado;
    }

    @Override
    public boolean modificarEstado(EstadoServicioVO estadoServicio) {
        boolean modificado=true;
        try{
            sql="update estadoservicio set nombreEstadoServicio=? where idEstadoServicio=?";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            sentencia.setObject(1, estadoServicio.getNombreEstadoServicio());
            sentencia.setObject(2, estadoServicio.getIdEstadoServicio());
            sentencia.executeUpdate();
            modificado=true;
        }
        catch(Exception i){
            modificado=false;
            System.err.print("Error al insertar datos en los roles. "+i);
        }
        finally{
            Conexion.cerrar(sentencia,resultado);
        }
        return modificado;
    }

    @Override
    public boolean eliminarEstado(int id) {
        boolean eliminado=true;
        try{
            sql="delete from estadoservicio where idEstadoServicio="+id;
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            sentencia.executeUpdate();
            eliminado=true;
        }
        catch(Exception i){
            eliminado=false;
            System.err.print("Error al eliminar datos en la id"+id+" de la tabla estadoServicio. "+i);
        }
        finally{
            Conexion.cerrar(sentencia,resultado);
        }
        return eliminado;
    }
    
}
