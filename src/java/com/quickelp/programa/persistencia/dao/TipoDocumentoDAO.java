/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazTipoDoc;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.TipoDocumentoVO;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public class TipoDocumentoDAO  extends Conexion implements InterfazTipoDoc{
    Conexion claseConexion = new Conexion();
    //private Connection conexion;
    private PreparedStatement sentencia=null;
    private ResultSet resultado=null;
    private String sql="";
    private List<TipoDocumentoVO> listarTipoDocumento = new ArrayList<>();    
    
    
    @Override
    public List<TipoDocumentoVO> listadoTipoDoc() {
        sql="select * from tipoDocumento";
        try{
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                TipoDocumentoVO tipoDoc = new TipoDocumentoVO(resultado.getInt("idTipoDocumento"));
                tipoDoc.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                listarTipoDocumento.add(tipoDoc);
            }
            
        }
        catch(Exception l){
            System.err.print("Error al consultar la tabla de roles. "+l);
        }
        finally{
            Conexion.cerrar(sentencia, resultado);
        }
        return listarTipoDocumento;
    }

    @Override
    public boolean AgregarTipoDoc(TipoDocumentoVO tipoDocumento) {
        boolean insertado=true;
        try{
            sql="insert into tipoDocumento(idTipoDocumento, nombreTipoDoc) values(?,?)";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql); 
            
            sentencia.setObject(1, tipoDocumento.getIdTipoDocumento());
            sentencia.setObject(2, tipoDocumento.getNombreTipoDoc());
            
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
    public boolean ModificarTipoDoc(TipoDocumentoVO tipoDocumento) {
        boolean modificado=true;
        try{
            sql="update tipodocumento set nombreTipoDoc=? where idTipoDocumento=?";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            
            sentencia.setObject(1, tipoDocumento.getNombreTipoDoc());
            sentencia.setObject(2, tipoDocumento.getIdTipoDocumento());
            
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
    public boolean EliminarTipoDoc(int id) {
        boolean eliminado=true;
        try{
            sql="delete from tipoDocumento where idTipoDocumento="+id;
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            sentencia.executeUpdate();
            eliminado=true;
        }catch(Exception i){
            eliminado=false;
            System.err.print("Error al insertar datos en los roles. "+i);
        }
        finally{
            Conexion.cerrar(sentencia,resultado);
        }
        return eliminado;
    }
}
