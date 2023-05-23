/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.persistencia.vo.EquipoVO;
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
public class EquipoDAO {

    private Connection con;
    private PreparedStatement sentencia = null;
    private ResultSet rs = null;
    private String sql = null;
    private List<EquipoVO> lista = new ArrayList<>();

    public void insertar(EquipoVO EquipoVO) throws Exception {
        try {
            sql = "INSERT INTO equipo (Marca,Modelo,NumeroSerie,TipoEquipo,Observaciones) VALUES (?,?,?,?,?)";
            sentencia = con.prepareStatement(sql);
            sentencia.setObject(1, EquipoVO.getMarca());
            sentencia.setObject(2, EquipoVO.getModelo());
            sentencia.setObject(3, EquipoVO.getNumeroSerie());
            sentencia.setObject(4, EquipoVO.getTipoEquipo());
            sentencia.setObject(5, EquipoVO.getObservaciones());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }

    public void actualizar(EquipoVO EquipoVO) throws Exception {
        try {
            sql = "UPDATE equipo SET Marca=?,Modelo=?,NumeroSerie=?,TipoEquipo=?,Observaciones=? ";
            sentencia = con.prepareStatement(sql);
            sentencia.setObject(1, EquipoVO.getMarca());
            sentencia.setObject(2, EquipoVO.getModelo());
            sentencia.setObject(3, EquipoVO.getNumeroSerie());
            sentencia.setObject(4, EquipoVO.getTipoEquipo());
            sentencia.setObject(5, EquipoVO.getObservaciones());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }

    public void eliminar(EquipoVO EquipoVO) throws Exception {
        try {
            sql = "DELETE equipo where NumeroSerie=?";
            sentencia = con.prepareStatement(sql);
            sentencia.setObject(1, EquipoVO.getMarca());
            sentencia.setObject(2, EquipoVO.getModelo());
            sentencia.setObject(3, EquipoVO.getNumeroSerie());
            sentencia.setObject(4, EquipoVO.getTipoEquipo());
            sentencia.setObject(5, EquipoVO.getObservaciones());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }
}
