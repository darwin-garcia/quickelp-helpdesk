/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazTipoDoc;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.TipoDocumentoVO;
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
        sql="select * from tipoDoc";
        try{
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);
            resultado=sentencia.executeQuery();
            while(resultado.next()){
                TipoDocumentoVO tipoDoc = new TipoDocumentoVO(resultado.getInt("idTipoDoc"));
                tipoDoc.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                listarTipoDocumento.add(tipoDoc);
            }
            
        }
        catch(Exception l){
            System.err.print("Error al consultar la tabla de tipo documento "+l);
        }
        finally{
            Conexion.cerrar(sentencia, resultado);
        }
        return listarTipoDocumento;
    }

    @Override
    public TipoDocumentoVO ConsultarTipoDoc(int idTipoDoc){
        sql="select * from tipoDoc where idTipoDoc="+idTipoDoc;
        TipoDocumentoVO tipoDoc = new TipoDocumentoVO();
        try{
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                  tipoDoc.setIdTipoDocumento(resultado.getInt("idTipoDoc"));
                  tipoDoc.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
            }
        }catch(Exception e){
            System.err.println("DAO Error al consultar el tipo de Docuemento: "+idTipoDoc+" seleccionado. Excepcion: "+e);
        }finally{
            Conexion.cerrar(sentencia, resultado);
        }return tipoDoc;
    }
    
    @Override
    public boolean AgregarTipoDoc(TipoDocumentoVO tipoDocumento) {
        boolean insertado=true;
        try{
            sql="insert into tipoDoc(nombreTipoDoc) values(?)";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql); 
            
            sentencia.setObject(1, tipoDocumento.getNombreTipoDoc());
            
            sentencia.executeUpdate();
            insertado=true;
        }
        catch(Exception i){
            insertado=false;
            System.err.print("Error al insertar el tipo de documento. Estado: "+insertado+". Excepcion "+i);
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
            sql="update tipodoc set nombreTipoDoc=? where idTipoDoc=?";
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            
            sentencia.setObject(1, tipoDocumento.getNombreTipoDoc());
            sentencia.setObject(2, tipoDocumento.getIdTipoDocumento());
            
            sentencia.executeUpdate();
            modificado=true;
        }
        catch(Exception i){
            modificado=false;
            System.err.print("Error al modificar el tipo de documento. Estado:"+modificado+". Excepcion: "+i);
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
            sql="delete from tipoDoc where idTipoDoc="+id;
            conexion=claseConexion.obtenerConexion();
            sentencia=conexion.prepareStatement(sql);            
            sentencia.executeUpdate();
            eliminado=true;
        }catch(Exception i){
            eliminado=false;
            System.err.print("Error al eliminar el tipo de documento con id: "+id+". Excepcion"+i);
        }
        finally{
            Conexion.cerrar(sentencia,resultado);
        }
        return eliminado;
    }
}
