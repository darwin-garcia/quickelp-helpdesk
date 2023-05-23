/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;
import com.quickelp.programa.interfaz.InterfazRol;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.RolVO;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public class RolDAO extends Conexion implements InterfazRol{
    Conexion claseConexion = new Conexion();
    //private Connection conexion;
    private PreparedStatement sentencia=null;
    private ResultSet resultado=null;
    private String sql="";
    private List<RolVO> listar = new ArrayList<>();
    
    @Override
    public List<RolVO> listaRoles() {
        sql="select * from Rol";
        try{
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                RolVO rol = new RolVO(resultado.getInt("idRol"));
                rol.setNombreRol(resultado.getString("NombreRol"));
                listar.add(rol);
            }
            
        }
        catch(Exception l){
            System.err.print("Error al consultar la tabla de roles. "+l);
        }finally{
            Conexion.cerrar(sentencia, resultado);
        }
        return listar;
    }

    @Override
    public boolean AgregarRoles(RolVO rol) {
        boolean insertado=true;
        try{
            sql="insert into rol(idRol, nombreRol) values(?,?)";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            sentencia.setObject(1, rol.getIdRol());
            sentencia.setObject(2, rol.getNombreRol());
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
    public boolean ModificarRoles(RolVO rol) {
        boolean modificado=true;
        try{
            sql="update rol set nombreRol=? where idRol=?";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            
            sentencia.setObject(1, rol.getNombreRol());
            sentencia.setObject(2, rol.getIdRol());
            
            sentencia.executeUpdate();
            modificado=true;
        }
        catch(Exception i){
            modificado=false;
            System.err.print("Error al modificar datos en los roles. "+i);
        }
        finally{
            Conexion.cerrar(sentencia,resultado);
        }
        return modificado;
    }

    @Override
    public boolean EliminarRoles(int id) {
        boolean eliminado=true;
        try{
            sql="delete from rol where idRol="+id;
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            sentencia.executeUpdate();
            eliminado=true;
        }
        catch(Exception i){
            eliminado=false;
            System.err.print("Error al eliminar datos en la id"+id+" de la tabla roles. "+i);
        }
        finally{
            Conexion.cerrar(sentencia,resultado);
        }
        return eliminado;
    }
}
