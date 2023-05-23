/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.persistencia.vo.ServicioVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ServicioDAO {

    private Connection con;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql = null;
    private List<ServicioVO> lista = new ArrayList<>();
    
    public void insertar(ServicioVO ServicioVO ) throws Exception{
        try {
            sql = "INSERT INTO solicitudservicio (fechaSolicitud,descripcionServicio) VALUES (?,?)";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ServicioVO.getFechaSolicitud());
            ps.setObject(2, ServicioVO.getDescripcionServicio());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al ingresar datos");
        }
    }
    public void consultar(ServicioVO ServicioVO ) throws Exception{
        try {
            sql = "SELECT * FROM solicitudservicio WHERE idServicio=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ServicioVO.getFechaSolicitud());
            ps.setObject(2, ServicioVO.getDescripcionServicio());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar datos");
        }
    }
    public void cancelar(ServicioVO ServicioVO ) throws Exception{
        try {
            sql = "DELETE Servicio WHERE idServicio=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, ServicioVO.getFechaSolicitud());
            ps.setObject(2, ServicioVO.getDescripcionServicio());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar datos");
        }
    }

}
