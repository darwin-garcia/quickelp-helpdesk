/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazTipoServicio;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.TipoServicioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public class TipoServicioDAO extends Conexion implements InterfazTipoServicio{
    Conexion claseConexion = new Conexion();
    private PreparedStatement sentencia=null;
    private ResultSet resultado=null;
    private String sql="";
    private List<TipoServicioVO> lista = new ArrayList<>();
    @Override
    public List<TipoServicioVO> listar() {
        sql="select * from tiposervicio";
        try{
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                TipoServicioVO tipoServicio = new TipoServicioVO();
                tipoServicio.setIdTipoSer(resultado.getInt("idTipoSer"));
                tipoServicio.setNomTipoServicio(resultado.getString("nomTipoServicio"));
                lista.add(tipoServicio);
            }
        }
        catch(Exception l){
            System.err.print("Error al consultar la tabla de tipos de servicio. "+l);
        }
        finally{
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;
    }

    @Override
    public boolean agregarTipo(TipoServicioVO tipoServicio) {
        boolean insertado;
        try{
            sql="insert into tiposervicio(nomTipoServicio) values(?)";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            sentencia.setObject(1, tipoServicio.getNomTipoServicio());
            sentencia.executeUpdate();
            insertado=true;
        }catch(Exception i){
            insertado=false;
            System.err.print("Error al insertar datos en los roles. "+i);
        }finally{
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    @Override
    public boolean eliminarTipo(int id) {
        boolean eliminado;
        try{
            sql="delete from tipoServicio where idTipoSer="+id;
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            sentencia.executeUpdate();
            eliminado=true;
        }catch(Exception i){
            eliminado=false;
            System.err.print("Error al eliminar datos en la id: "+id+" de la tabla TipoServicio"+i);
        }finally{
            Conexion.cerrar(sentencia, resultado);
        }
        return eliminado;
    }
    
}
