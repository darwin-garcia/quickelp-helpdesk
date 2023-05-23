/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.persistencia.vo.ReparacionVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ReparacionDAO {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    private List<ReparacionVO> lista = new ArrayList<>();

    public void insertar(ReparacionVO ReparacionVO) throws Exception {
        try {
            sql = "INSERT INTO Reparacion (FechaEntrega,ObservacionesProceso,Garantia,FechaFinalizacion) VALUES (?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ReparacionVO.getFechaEntrega());
            ps.setObject(2, ReparacionVO.getObservacionesProceso());
            ps.setObject(3, ReparacionVO.getGarantia());
            ps.setObject(4, ReparacionVO.getFechaFinalizacion());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }
    public void consultar(ReparacionVO ReparacionVO) throws Exception {
        try {
            sql = "SELECT * FROM Reparacion WHERE idReparacion=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ReparacionVO.getFechaEntrega());
            ps.setObject(2, ReparacionVO.getObservacionesProceso());
            ps.setObject(3, ReparacionVO.getGarantia());
            ps.setObject(4, ReparacionVO.getFechaFinalizacion());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }
    
}
