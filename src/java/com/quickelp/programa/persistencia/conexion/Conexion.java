/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Darwin Garcia
 */
public class Conexion {
    private String usuario="root";
    private String passwd="";
    public Connection conexion;
    public Connection obtenerConexion() throws Exception{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/quickelp", usuario, passwd);
            return conexion;
        }
        catch(Exception x){
            x.printStackTrace();
            throw new Exception("No se puede conectar a la base de datos de Quickelp. Mensaje: "+x);
        }
    }
    public static void cerrar(PreparedStatement sentencia, ResultSet resultado, Connection conexion){
        try{
            if(resultado!=null){
                resultado.close();
            }
            if(sentencia!=null){
                sentencia.close();
            }
            if(conexion!=null){
                conexion.close();
            }
        }catch(Exception x){
            x.printStackTrace();
        }
    }
    public static void cerrar(PreparedStatement sentencia, ResultSet resultado){
        cerrar(sentencia, resultado, null);
    }
    public static void cerrar(PreparedStatement sentencia){
        cerrar(sentencia, null, null);
    }
    public static void cerrar(Connection conexion){
        cerrar(null, null, conexion);
    }
}
