/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazDiagnostico;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.DiagnosticoVO;
import com.quickelp.programa.persistencia.vo.EquipoVO;
import com.quickelp.programa.persistencia.vo.EstadoVO;
import com.quickelp.programa.persistencia.vo.MarcaVO;
import com.quickelp.programa.persistencia.vo.ServicioVO;
import com.quickelp.programa.persistencia.vo.TipoDocumentoVO;
import com.quickelp.programa.persistencia.vo.TipoServicioVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import com.quickelp.programa.persistencia.vo.Usuario_TipoServicioVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class DiagnosticoDAO extends Conexion implements InterfazDiagnostico {

    //Llamo la clase Conexion
    Conexion claseConexion = new Conexion();
    //Llamo directamente la sentencia
    private PreparedStatement sentencia = null;
    //Llamo un Resultado de la base de datos
    private ResultSet resultado = null;
    private String sql = "";
    private List<DiagnosticoVO> lista = new ArrayList<>();
    private List<DiagnosticoVO> listaTecnico = new ArrayList<>();

    @Override
    public List<DiagnosticoVO> listadoDiagnosticos() {
        //Consulta de MySQL
        sql = "select d.idDiagnostico, es.nombreEstado, u. nombre, u.apellido, u.telefono, u.direccion, \n"
                + "s.fechaSolicitud, s.descripcion, \n"
                + "e.tipoEquipo, m.NombreMarca, e.modelo, e.numSerial,\n"
                + "d.fechaRecepcion, d.Solucion, \n"
                + "ut.nombre, ut.apellido, ts.nomtipoServicio  \n"
                + "from Diagnostico d\n"
                + "inner join Servicio s on s.idServicio=d.idServicio\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join equipo e on e.idEquipo=s.idequipo\n"
                + "inner join marca m on m.idMarca=e.idMarca\n"
                + "inner join tipodoc td on td.idtipodoc=u.idtipodoc\n"
                + "inner join estado es on es.idestado=s.idestado\n"
                + "inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer\n"
                + "inner join tipoServicio ts on uts.idtiposer=ts.idtiposer\n"
                + "inner join usuario ut on uts.idusuario=ut.idusuario\n"
                + "order by s.fechasolicitud desc;";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            //Ciclo que permite listar la informacion a partir de la consulta
            while (resultado.next()) {
                DiagnosticoVO diagnostico = new DiagnosticoVO();
                ServicioVO servicio = new ServicioVO();
                UsuarioVO usuario = new UsuarioVO();// Tablas Dependientes
                TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
                TipoServicioVO tipoServicio = new TipoServicioVO();
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                EstadoVO estado = new EstadoVO();
                Usuario_TipoServicioVO usuario_tiposer = new Usuario_TipoServicioVO();
                UsuarioVO funcionario = new UsuarioVO();
                //-----------------------------------------------------------------------
                diagnostico.setIdDiagnostico(resultado.getInt("idDiagnostico"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                usuario.setNombre(resultado.getString("u.nombre"));//Alias del Usuario Cliente
                usuario.setApellido(resultado.getString("u.apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setDireccion(resultado.getString("direccion"));
                servicio.setFechaSolicitud(resultado.getDate("fechaSolicitud"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
//                servicio.setIdServicio(resultado.getInt("idServicio"));
                diagnostico.setFechaRecepcion(resultado.getDate("fechaRecepcion"));
                diagnostico.setSolucion(resultado.getString("solucion"));
                funcionario.setNombre(resultado.getString("ut.nombre"));//Alias del Usuario Tecnico
                funcionario.setApellido(resultado.getString("ut.apellido"));
                tipoServicio.setNomTipoServicio(resultado.getString("nomTipoServicio"));

                //-----------------------------------------------------------------------
                diagnostico.setIdServicio(servicio); // inner join Servicio s on s.idServicio=d.idServicio            
                servicio.setIdUsuario(usuario);// inner join usuario u on u.idusuario=s.idusuario
                usuario.setTipoIdentificacion(tipoDocumento); //inner join tipodoc td on td.idtipodoc=u.idtipodoc
                servicio.setIdEquipo(equipo);// inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo); // inner join marca m on e.idmarca=m.idmarca
                servicio.setIdEstado(estado);// inner join estado es on es.idestado=s.idestado
                usuario_tiposer.setIdUsuario(funcionario);
                usuario_tiposer.setIdTipoSer(tipoServicio);

                lista.add(diagnostico);
            }

        } catch (Exception l) {
            System.out.println("Error al consultar los usuarios en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return lista;
    }

    @Override
    public List<DiagnosticoVO> consultarDiagnosticos(int id) {
        //Consulta de MySQL
        sql = "select d.idDiagnostico, es.nombreEstado, s.fechaSolicitud,\n"
                + "u.nombre, u.apellido, u.telefono, u.direccion, \n"
                + "s.descripcion, d.fechaRecepcion, d.Solucion,\n"
                + "e.tipoEquipo, m.NombreMarca, e.modelo, e.numSerial,\n"
                + "ut.nombre, ut.apellido, ts.nomtipoServicio \n"
                + "from Diagnostico d \n"
                + "inner join Servicio s on s.idServicio=d.idServicio\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join equipo e on e.idEquipo=s.idequipo\n"
                + "inner join marca m on m.idMarca=e.idMarca\n"
                + "inner join tipodoc td on td.idtipodoc=u.idtipodoc\n"
                + "inner join estado es on es.idestado=s.idestado\n"
                + "inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer\n"
                + "inner join tipoServicio ts on uts.idtiposer=ts.idtiposer\n"
                + "inner join usuario ut on uts.idusuario=ut.idusuario\n"
                + "where ut.idUsuario="+id+ " and s.idEstado=1 order by s.fechasolicitud desc;";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            //Ciclo que permite listar la informacion a partir de la consulta
            while (resultado.next()) {
                DiagnosticoVO diagnostico = new DiagnosticoVO();
                ServicioVO servicio = new ServicioVO();
                UsuarioVO usuario = new UsuarioVO();// Tablas Dependientes
                TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
                TipoServicioVO tipoServicio = new TipoServicioVO();
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                EstadoVO estado = new EstadoVO();
                Usuario_TipoServicioVO usuario_tiposer = new Usuario_TipoServicioVO();
                UsuarioVO funcionario = new UsuarioVO();
                //-----------------------------------------------------------------------
                diagnostico.setIdDiagnostico(resultado.getInt("idDiagnostico"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                servicio.setFechaSolicitud(resultado.getDate("s.fechaSolicitud"));
                usuario.setNombre(resultado.getString("u.nombre"));//Alias del Usuario Cliente
                usuario.setApellido(resultado.getString("u.apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setDireccion(resultado.getString("direccion"));                
                servicio.setDescripcion(resultado.getString("descripcion"));
                diagnostico.setFechaRecepcion(resultado.getDate("d.fechaRecepcion"));
                diagnostico.setSolucion(resultado.getString("solucion"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));              
                funcionario.setNombre(resultado.getString("ut.nombre"));//Alias del Usuario Tecnico
                funcionario.setApellido(resultado.getString("ut.apellido"));
                tipoServicio.setNomTipoServicio(resultado.getString("nomTipoServicio"));

                //-----------------------------------------------------------------------
                diagnostico.setIdServicio(servicio); // inner join Servicio s on s.idServicio=d.idServicio            
                servicio.setIdUsuario(usuario);// inner join usuario u on u.idusuario=s.idusuario
                usuario.setTipoIdentificacion(tipoDocumento); //inner join tipodoc td on td.idtipodoc=u.idtipodoc
                servicio.setIdEquipo(equipo);// inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo); // inner join marca m on e.idmarca=m.idmarca
                servicio.setIdEstado(estado);// inner join estado es on es.idestado=s.idestado
                usuario_tiposer.setIdUsuario(funcionario);
                usuario_tiposer.setIdTipoSer(tipoServicio);
                servicio.setIdTipoSer(usuario_tiposer);//inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer
                
                listaTecnico.add(diagnostico);
            }

        } catch (Exception l) {
            System.out.println("Error al consultar los usuarios en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listaTecnico;
    }

    @Override
    public boolean AgregarDiagnostico(DiagnosticoVO diagnostico) {
        boolean insertado;
        
        try {
            sql = "call registrarDiagnostico(?,?)";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.setObject(1, diagnostico.getIdServicio().getIdServicio());
            sentencia.setObject(2, diagnostico.getSolucion());
            //Ejecuto la consulta
            sentencia.executeUpdate();
            insertado = true;
        } catch (Exception c) {
            insertado = false;
            System.err.print("DAO Error al registrar el informe de diagnostico. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    @Override
    public boolean ModificarDiagnostico(DiagnosticoVO diagnostico) {
        boolean modificado;
        try {
            //Consulta de MySQL
            sql = "";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Atributos que permiten modificar la informacion de la tabla Diagnosticos

            //Ejecuto la consulta
            sentencia.executeUpdate();
            modificado = true;
        } catch (Exception c) {
            modificado = false;
            System.err.print("Error al modificar el usuario en la base de datos con la id. " + diagnostico.getIdDiagnostico() + ". Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return modificado;
    }
}
