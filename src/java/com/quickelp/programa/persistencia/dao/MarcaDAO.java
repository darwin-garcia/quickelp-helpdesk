/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazMarcaPC;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.MarcaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public class MarcaDAO extends Conexion implements InterfazMarcaPC {
    //Llamo la clase Conexion
    Conexion claseConexion = new Conexion();
    //Llamo directamente la sentencia
    private PreparedStatement sentencia = null;
    //Llamo un Resultado de la base de datos
    private ResultSet resultado = null;
    private String sql = "";
    //Llamo una lista con informacion del UsuarioVO
    private List<MarcaVO> lista = new ArrayList<>();

    @Override
    public List <MarcaVO> listadoMarca() {
        sql="select * from Marca";
        try{            
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            
            while(resultado.next()){
                MarcaVO marca = new MarcaVO();
                marca.setIdMarca(resultado.getInt("idMarca"));
                marca.setNombreMarca(resultado.getString("nombreMarca"));
                lista.add(marca);
            }
            
        }catch (Exception l) {
            System.out.println("Error al consultar las marcas de equipos en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;
    }

    @Override
    public MarcaVO consultarMarca(int idMarca) {
        sql="select * from Marca where idMarca="+idMarca;
        MarcaVO marca = new MarcaVO();
        try{
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while(resultado.next()){
                marca.setIdMarca(resultado.getInt("idMarca"));
                marca.setNombreMarca(resultado.getString("nombreMarca"));
            }
            
        } catch (Exception c) {
            System.err.print("Error al consultar la marca de referencia con la id: " + idMarca + " , seleccionado. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return marca;
    }

    @Override
    public boolean AgregarMarca(MarcaVO marca) {
        boolean insertado;
        try{
            sql="insert into Marca(nombreMarca) values(?)";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.setObject(1, marca.getNombreMarca());
            sentencia.executeUpdate();
            insertado=true;
        } catch (Exception c) {
            insertado = false;
            System.err.print("Error al ingresar la marca de referencia en la base de datos. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    @Override
    public boolean EliminarMarca(int id) {
        boolean eliminado;
        try {
            sql = "delete from marca where idMarca=" + id;
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.executeUpdate();
            eliminado = true;
        } catch (Exception c) {
            eliminado = false;
            System.err.print("Error al eliminar la marca de referencia en la base de datos. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return eliminado;
    }
    
}
