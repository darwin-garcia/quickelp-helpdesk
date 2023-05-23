/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.persistencia.vo.UbicacionVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class UbicacionDAO {
     private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    private List<UbicacionVO> lista = new ArrayList<>();
    
    public void insertar(UbicacionVO UbicacionVO ) throws Exception{
        try {
            sql = "INSERT INTO UbicacionVO (direccion,barrio,localidad) VALUES (?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setObject(1, UbicacionVO.getDireccion());
            ps.setObject(2, UbicacionVO.getBarrio());
            ps.setObject(2, UbicacionVO.getLocalidad());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }
    public void consultar(UbicacionVO UbicacionVO ) throws Exception{
        try {
            sql = "SELECT * FROM Ubicacion WHERE idUbicacion=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, UbicacionVO.getDireccion());
            ps.setObject(2, UbicacionVO.getBarrio());
            ps.setObject(2, UbicacionVO.getLocalidad());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }
}
