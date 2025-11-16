/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.quickelp.programa.persistencia.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Darwin Garcia
 */
public class Prueba1 {
    
    public Prueba1() {
    }
    
    @Test
    public void testDatos(){
        Conexion conexion = new Conexion();
        Statement ps = null;
        ResultSet rs;
        Scanner entrada = new Scanner(System.in);
        try{
//            String sql="select * from Usuario";
//            ps=con.obtenerConexion();
//            rs=ps.executeQuery();
            System.out.println("Llegue a las pruebas ::)");
            
            
                
            conexion.obtenerConexion();
            if(conexion != null){
                System.out.println("Conectado.");
                                
                String sql="select * from usuario";
                //con.obtenerConexion();
                ps=conexion.conexion.prepareStatement(sql);
                rs=ps.executeQuery(sql);
            }else{
                System.out.println("No puedo conectarme");
            }
            
        }catch(Exception e){
            System.err.println("Imposible conectarse a la base de datos");
        }
    }    
   

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
