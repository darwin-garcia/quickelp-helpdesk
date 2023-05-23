/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.persistencia.vo.DiagnosticoVO;
import com.quickelp.programa.persistencia.vo.ServicioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class DiagnosticoDAO {

    private Connection con;
    private PreparedStatement sentencia = null;
    private ResultSet rs = null;
    private String sql = null;
    private List<DiagnosticoVO> lista = new ArrayList<>();

    public void insertar(DiagnosticoVO DiagnosticoVO) throws Exception {
        try {
            sql = "INSERT INTO Diagnostico (fechaRecepcion,observaciones,posibleSolucion ) VALUES (?,?,?)";
             sentencia = con.prepareStatement(sql);
             sentencia.setObject(1, DiagnosticoVO.getFechaRecepcion());
             sentencia.setObject(2, DiagnosticoVO.getObservaciones());
             sentencia.setObject(3, DiagnosticoVO.getPosibleSolucion());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }
    public void actualizar(DiagnosticoVO DiagnosticoVO) throws Exception {
        try {
            sql = "UPDATE Diagnostico SET fechaRecepcion=?,observaciones=?,posibleSolucion=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setObject(1, DiagnosticoVO.getFechaRecepcion());
            sentencia.setObject(2, DiagnosticoVO.getObservaciones());
            sentencia.setObject(3, DiagnosticoVO.getPosibleSolucion());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }
}
