/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.dao;

import com.quickelp.programa.interfaz.InterfazServicio;
import com.quickelp.programa.persistencia.conexion.Conexion;
import com.quickelp.programa.persistencia.vo.EquipoVO;
import com.quickelp.programa.persistencia.vo.EstadoVO;
import com.quickelp.programa.persistencia.vo.MarcaVO;
import com.quickelp.programa.persistencia.vo.ServicioVO;
import com.quickelp.programa.persistencia.vo.TipoDocumentoVO;
import com.quickelp.programa.persistencia.vo.TipoServicioVO;
import com.quickelp.programa.persistencia.vo.UsuarioVO;
import com.quickelp.programa.persistencia.vo.Usuario_TipoServicioVO;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class ServicioDAO extends Conexion implements InterfazServicio {

    //Llamo la clase Conexion
    Conexion claseConexion = new Conexion();
    //Llamo directamente la sentencia
    private PreparedStatement sentencia = null;
    //Llamo un Resultado de la base de datos
    private ResultSet resultado = null;
    private String sql = "";
    private List<ServicioVO> listaActivos = new ArrayList<>();
    private List<ServicioVO> listaInactivos = new ArrayList<>();
    private List<ServicioVO> listaClientes = new ArrayList<>();
    //Variable de listar servicios por Rol
    private List<ServicioVO> listadoporCliente = new ArrayList<>();
    private List<ServicioVO> listadoporTecnico = new ArrayList<>();
    private List<ServicioVO> listadopendienteCli = new ArrayList<>();

    @Override
    public List<ServicioVO> listadoServiciosActivosAdmin() {
        sql = "select s.idServicio, s.fechaSolicitud, td.nombretipoDoc, u.numIdentidad, u.nombre, u.apellido, u.telefono, u.correo, u.direccion, e.tipoEquipo, m.nombreMarca, e.modelo, e.numSerial,\n"
                + "s.descripcion, es.nombreEstado, ut.nombre, ut.apellido, ts.nomtipoServicio  \n"
                + "from Servicio s\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join tipodoc td on td.idtipodoc=u.idtipodoc\n"
                + "inner join equipo e on e.idequipo=s.idequipo\n"
                + "inner join marca m on e.idmarca=m.idmarca\n"
                + "inner join estado es on es.idestado=s.idestado\n"
                + "inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer\n"
                + "inner join tipoServicio ts on uts.idtiposer=ts.idtiposer\n"
                + "inner join usuario ut on uts.idusuario=ut.idusuario\n"
                + "where es.nombreEstado='Activo'\n"
                + "order by s.fechasolicitud desc;";
        try {
            //Obtengo la conexion
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                ServicioVO servicio = new ServicioVO();// Tabla Principal
                UsuarioVO usuario = new UsuarioVO();// Tablas Dependientes
                TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
                TipoServicioVO tipoServicio = new TipoServicioVO();
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                EstadoVO estado = new EstadoVO();
                Usuario_TipoServicioVO usuario_tiposer = new Usuario_TipoServicioVO();
                UsuarioVO funcionario = new UsuarioVO();
                //----------------------------------------------------------------------------------
                servicio.setIdServicio(resultado.getInt("idServicio"));
                servicio.setFechaSolicitud(resultado.getDate("fechaSolicitud"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                usuario.setNumeroIdentificacion(resultado.getLong("numIdentidad"));
                usuario.setNombre(resultado.getString("u.nombre"));//Alias del Usuario Cliente
                usuario.setApellido(resultado.getString("u.apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                funcionario.setNombre(resultado.getString("ut.nombre"));//Alias del Usuario Tecnico
                funcionario.setApellido(resultado.getString("ut.apellido"));
                tipoServicio.setNomTipoServicio(resultado.getString("nomTipoServicio"));
                //----------------------------------------------------------------------------------
                servicio.setIdUsuario(usuario);// inner join usuario u on u.idusuario=s.idusuario
                usuario.setTipoIdentificacion(tipoDocumento); //inner join tipodoc td on td.idtipodoc=u.idtipodoc
                servicio.setIdEquipo(equipo);// inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo); // inner join marca m on e.idmarca=m.idmarca
                servicio.setIdEstado(estado);// inner join estado es on es.idestado=s.idestado
                usuario_tiposer.setIdUsuario(funcionario);
                usuario_tiposer.setIdTipoSer(tipoServicio);
                servicio.setIdTipoSer(usuario_tiposer);//inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer

                //Comando para Agregar los datos cuando recorre el ArrayList
                listaActivos.add(servicio);
            }
        } catch (Exception l) {
            System.out.println("DAO Imposible consultar los servicios activos en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listaActivos;
    }

    @Override
    public List<ServicioVO> listadoServiciosInactivosAdmin() {
        sql = "select s.idServicio, s.fechaSolicitud, td.nombretipoDoc, u.numIdentidad, u.nombre, u.apellido, u.telefono, u.correo, u.direccion, e.tipoEquipo, m.nombreMarca, e.modelo, e.numSerial,\n"
                + "s.descripcion, es.nombreEstado, ut.nombre, ut.apellido, ts.nomtipoServicio  \n"
                + "from Servicio s\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join tipodoc td on td.idtipodoc=u.idtipodoc\n"
                + "inner join equipo e on e.idequipo=s.idequipo\n"
                + "inner join marca m on e.idmarca=m.idmarca\n"
                + "inner join estado es on es.idestado=s.idestado\n"
                + "inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer\n"
                + "inner join tipoServicio ts on uts.idtiposer=ts.idtiposer\n"
                + "inner join usuario ut on uts.idusuario=ut.idusuario\n"
                + "where es.nombreEstado='Inactivo'\n"
                + "order by s.fechasolicitud desc;";
        try {
            //Obtengo la conexion
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                ServicioVO servicio = new ServicioVO();// Tabla Principal
                UsuarioVO usuario = new UsuarioVO();// Tablas Dependientes
                TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
                TipoServicioVO tipoServicio = new TipoServicioVO();
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                EstadoVO estado = new EstadoVO();
                Usuario_TipoServicioVO usuario_tiposer = new Usuario_TipoServicioVO();
                UsuarioVO funcionario = new UsuarioVO();
                //----------------------------------------------------------------------------------
                servicio.setIdServicio(resultado.getInt("idServicio"));
                servicio.setFechaSolicitud(resultado.getDate("fechaSolicitud"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                usuario.setNumeroIdentificacion(resultado.getLong("numIdentidad"));
                usuario.setNombre(resultado.getString("u.nombre"));//Alias del Usuario Cliente
                usuario.setApellido(resultado.getString("u.apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                funcionario.setNombre(resultado.getString("ut.nombre"));//Alias del Usuario Tecnico
                funcionario.setApellido(resultado.getString("ut.apellido"));
                tipoServicio.setNomTipoServicio(resultado.getString("nomTipoServicio"));
                //----------------------------------------------------------------------------------
                servicio.setIdUsuario(usuario);// inner join usuario u on u.idusuario=s.idusuario
                usuario.setTipoIdentificacion(tipoDocumento); //inner join tipodoc td on td.idtipodoc=u.idtipodoc
                servicio.setIdEquipo(equipo);// inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo); // inner join marca m on e.idmarca=m.idmarca
                servicio.setIdEstado(estado);// inner join estado es on es.idestado=s.idestado
                usuario_tiposer.setIdUsuario(funcionario);
                usuario_tiposer.setIdTipoSer(tipoServicio);
                servicio.setIdTipoSer(usuario_tiposer);//inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer
                //Comando para Agregar los datos cuando recorre el ArrayList
                listaInactivos.add(servicio);
            }
        } catch (Exception l) {
            System.out.println("DAO Imposible consultar los servicios activos en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listaInactivos;
    }

    // Lista de todos los Servicios que solicitan los clientes
    @Override
    public List<ServicioVO> listadoServiciosClientes() {
        sql = "select s.idServicio, s.fechaSolicitud, td.nombretipoDoc, u.numIdentidad, u.nombre, u.apellido, u.telefono, u.correo, u.direccion, e.tipoEquipo, m.nombreMarca, e.modelo, e.numSerial, ts.nomTipoServicio, s.descripcion,  es.nombreEstado\n"
                + "from Servicio s\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join tipodoc td on td.idtipodoc=u.idtipodoc\n"
                + "inner join equipo e on e.idequipo=s.idequipo\n"
                + "inner join marca m on e.idmarca=m.idmarca\n"
                + "inner join estado es on es.idestado=s.idestado\n"
                + "inner join tipoServicio ts on ts.idTipoSer=s.idTipoDelServ\n"
                + "where es.nombreEstado='Activo'\n"
                + "order by s.fechasolicitud desc";
        try {
            //Obtengo la conexion
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                ServicioVO servicio = new ServicioVO();// Tabla Principal
                UsuarioVO usuario = new UsuarioVO();// Tablas Dependientes
                TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
                TipoServicioVO tipoServicio = new TipoServicioVO();
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                EstadoVO estado = new EstadoVO();
                //-------------------------------------------------------------------------------------------
                servicio.setIdServicio(resultado.getInt("idServicio"));
                servicio.setFechaSolicitud(resultado.getDate("fechaSolicitud"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("nombreTipoDoc"));
                usuario.setNumeroIdentificacion(resultado.getLong("numIdentidad"));
                usuario.setNombre(resultado.getString("u.nombre"));//Alias del Usuario Cliente
                usuario.setApellido(resultado.getString("u.apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                tipoServicio.setNomTipoServicio(resultado.getString("nomTipoServicio"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                //------------------------------------------------------------------------------------------------
                servicio.setIdUsuario(usuario);// inner join usuario u on u.idusuario=s.idusuario
                usuario.setTipoIdentificacion(tipoDocumento); //inner join tipodoc td on td.idtipodoc=u.idtipodoc
                servicio.setIdEquipo(equipo);// inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo); // inner join marca m on e.idmarca=m.idmarca                
                servicio.setIdTipoDelServ(tipoServicio);//inner join tipoServicio ts on ts.idTipoSer=s.idTipoDelServ                
                servicio.setIdEstado(estado);// inner join estado es on es.idestado=s.idestado
                listaClientes.add(servicio);
            }
        } catch (Exception l) {
            System.out.println("DAO Imposible consultar los servicios solicitados por clientes en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listaClientes;
    }

    public List<ServicioVO> buscarServiciosPendientes(int id) {
        sql = "select s.idServicio, s.fechaSolicitud, tp.nomTipoServicio,\n"
                + "e.tipoEquipo, m.nombreMarca, e.modelo, e.numSerial,\n"
                + "s.descripcion, es.nombreEstado\n"
                + "from Servicio s\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join tipoServicio tp on tp.idTipoSer=s.idTipoDelServ\n"
                + "inner join equipo e on e.idequipo=s.idequipo\n"
                + "inner join marca m on e.idmarca=m.idmarca\n"
                + "inner join estado es on es.idestado=s.idestado\n"
                + "where u.idUsuario=" + id + " order by s.fechasolicitud desc, es.idEstado asc;";
        try {
            //Obtengo la conexion
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //----------------VO requierido para las relaciones foraneas-------------------
                ServicioVO servicio = new ServicioVO();// Tabla Principal
                TipoServicioVO tipoP = new TipoServicioVO();//Tablas Dependientes
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                EstadoVO estado = new EstadoVO();
                //------------------Atributos requeridos para mostrar en la vista-------------------------
                servicio.setIdServicio(resultado.getInt("idServicio"));
                servicio.setFechaSolicitud(resultado.getDate("fechaSolicitud"));
                tipoP.setNomTipoServicio(resultado.getString("tp.nomTipoServicio"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                //-------Requerido cuando se usa inner join para unir la informacion de la consulta SQL---------
                servicio.setIdTipoDelServ(tipoP);//inner join tipoServicio tp on tp.idTipoSer=s.idTipoDelServ
                servicio.setIdEquipo(equipo);//inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo); //inner join marca m on e.idmarca=m.idmarca
                servicio.setIdEstado(estado); //inner join estado es on es.idestado=s.idestado
                listadopendienteCli.add(servicio);

            }
        } catch (Exception e) {
            System.out.println("DAO Imposible consultar los servicios solicitados pendientes por atencion al cliente en la base de datos. Excepcion: " + e);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listadopendienteCli;

    }

    //********************************************************************************************************
    @Override
    public List<ServicioVO> buscarServiciosxIdCliente(int id) {
        sql = "select s.idServicio, s.fechaSolicitud, tp.nomTipoServicio,\n"
                + "e.tipoEquipo, m.nombreMarca, e.modelo, e.numSerial,\n"
                + "s.descripcion, es.nombreEstado, ut.nombre, ut.apellido, ts.nomtipoServicio  \n"
                + "from Servicio s\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join tipoServicio tp on tp.idTipoSer=s.idTipoDelServ\n"
                + "inner join equipo e on e.idequipo=s.idequipo\n"
                + "inner join marca m on e.idmarca=m.idmarca\n"
                + "inner join estado es on es.idestado=s.idestado\n"
                + "inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer\n"
                + "inner join tipoServicio ts on uts.idtiposer=ts.idtiposer\n"
                + "inner join usuario ut on uts.idusuario=ut.idusuario\n"
                + "where u.idUsuario=" + id + " order by s.fechasolicitud desc, es.idEstado asc;";
        try {
            //Obtengo la conexion
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //----------------VO requierido para las relaciones foraneas-------------------
                ServicioVO servicio = new ServicioVO();// Tabla Principal
                TipoServicioVO tipoP = new TipoServicioVO();//Tablas Dependientes
                TipoServicioVO tipoS = new TipoServicioVO();//Tablas Dependientes
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                EstadoVO estado = new EstadoVO();
                Usuario_TipoServicioVO usuario_tiposer = new Usuario_TipoServicioVO();
                UsuarioVO funcionario = new UsuarioVO();
                //------------------Atributos requeridos para mostrar en la vista-------------------------
                servicio.setIdServicio(resultado.getInt("idServicio"));
                servicio.setFechaSolicitud(resultado.getDate("fechaSolicitud"));
                tipoP.setNomTipoServicio(resultado.getString("tp.nomTipoServicio"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                funcionario.setNombre(resultado.getString("ut.nombre"));//Alias del Usuario Tecnico
                funcionario.setApellido(resultado.getString("ut.apellido"));
                tipoS.setNomTipoServicio(resultado.getString("ts.nomTipoServicio"));
                //-------Requerido cuando se usa inner join para unir la informacion de la consulta SQL---------
                servicio.setIdTipoDelServ(tipoP);//inner join tipoServicio tp on tp.idTipoSer=s.idTipoDelServ
                servicio.setIdEquipo(equipo);//inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo); //inner join marca m on e.idmarca=m.idmarca
                servicio.setIdEstado(estado); //inner join estado es on es.idestado=s.idestado
                servicio.setIdTipoSer(usuario_tiposer);//inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer
                usuario_tiposer.setIdTipoSer(tipoS);//inner join tipoServicio ts on uts.idtiposer=ts.idtiposer
                usuario_tiposer.setIdUsuario(funcionario); //inner join usuario ut on uts.idusuario=ut.idusuario
                listadoporCliente.add(servicio);
            }

        } catch (Exception l) {
            System.out.println("DAO Imposible consultar los servicios solicitados por cliente ID:" + id + " en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listadoporCliente;
    }

    @Override
    public List<ServicioVO> buscarServiciosxIdTecnico(int id) {
        sql = "select s.idServicio, s.fechaSolicitud, tp.nomTipoServicio,\n"
                + "u.numIdentidad, u.nombre, u.apellido, u.telefono, u.correo, u.direccion, \n"
                + "es.nombreEstado, s.descripcion,\n"
                + "e.tipoEquipo, m.nombreMarca, e.modelo, e.numSerial\n"
                + "from Servicio s\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join tipodoc td on td.idtipodoc=u.idtipodoc\n"
                + "inner join tipoServicio tp on tp.idTipoSer=s.idTipoDelServ\n"
                + "inner join equipo e on e.idequipo=s.idequipo\n"
                + "inner join marca m on e.idmarca=m.idmarca\n"
                + "inner join estado es on es.idestado=s.idestado\n"
                + "inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer\n"
                + "inner join tipoServicio ts on uts.idtiposer=ts.idtiposer\n"
                + "inner join usuario ut on uts.idusuario=ut.idusuario\n"
                + "where es.nombreEstado='Activo' and ut.idUsuario=" + id + " and ut.idRol=2\n"
                + "order by s.fechasolicitud desc;";
        try {
            //Obtengo la conexion
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Ejecuto la consulta
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                //----------------VO requierido para las relaciones foraneas-------------------
                ServicioVO servicio = new ServicioVO();// Tabla Principal
                UsuarioVO usuario = new UsuarioVO();// Tablas Dependientes
                TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
                TipoServicioVO tipoServicio = new TipoServicioVO();
                EquipoVO equipo = new EquipoVO();
                MarcaVO marcaEquipo = new MarcaVO();
                EstadoVO estado = new EstadoVO();
                Usuario_TipoServicioVO usuario_tiposer = new Usuario_TipoServicioVO();
                UsuarioVO funcionario = new UsuarioVO();
                //------------------Atributos requeridos para mostrar en la vista-------------------------
                servicio.setIdServicio(resultado.getInt("idServicio"));
                servicio.setFechaSolicitud(resultado.getDate("fechaSolicitud"));
                tipoServicio.setNomTipoServicio(resultado.getString("nomTipoServicio"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("numIdentidad"));
                usuario.setNumeroIdentificacion(resultado.getLong("numIdentidad"));
                usuario.setNombre(resultado.getString("u.nombre"));//Alias del Usuario Cliente
                usuario.setApellido(resultado.getString("u.apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                // Requerido cuando se usa inner join para unir la informacion de la consulta SQL
                servicio.setIdUsuario(usuario);//inner join usuario u on u.idusuario=s.idusuario
                usuario.setTipoIdentificacion(tipoDocumento);//inner join tipodoc td on td.idtipodoc=u.idtipodoc
                servicio.setIdTipoDelServ(tipoServicio);//inner join tipoServicio tp on tp.idTipoSer=s.idTipoDelServ
                servicio.setIdEquipo(equipo);//inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo);//inner join marca m on e.idmarca=m.idmarca
                servicio.setIdEstado(estado);//inner join estado es on es.idestado=s.idestado
                servicio.setIdTipoSer(usuario_tiposer);//inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer
                usuario_tiposer.setIdTipoSer(tipoServicio);//inner join tipoServicio ts on uts.idtiposer=ts.idtiposer
                usuario_tiposer.setIdUsuario(funcionario);//inner join usuario ut on uts.idusuario=ut.idusuario
                listadoporTecnico.add(servicio);
            }
        } catch (Exception l) {
            System.out.println("DAO Imposible consultar los servicios asignados por tecnico con Usuario ID:" + id + " en la base de datos. Excepcion: " + l);

        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return listadoporTecnico;
    }

    @Override
    public ServicioVO consultarServicio(int idServicio) {
        sql = "select s.idServicio, s.fechaSolicitud, tp.nomTipoServicio,\n"
                + "u.numIdentidad, u.nombre, u.apellido, u.telefono, u.correo, u.direccion, \n"
                + "es.nombreEstado, s.descripcion,\n"
                + "e.tipoEquipo, m.nombreMarca, e.modelo, e.numSerial\n"
                + "from Servicio s\n"
                + "inner join usuario u on u.idusuario=s.idusuario\n"
                + "inner join tipodoc td on td.idtipodoc=u.idtipodoc\n"
                + "inner join tipoServicio tp on tp.idTipoSer=s.idTipoDelServ\n"
                + "inner join equipo e on e.idequipo=s.idequipo\n"
                + "inner join marca m on e.idmarca=m.idmarca\n"
                + "inner join estado es on es.idestado=s.idestado where s.idServicio="+idServicio;
        ServicioVO servicio = new ServicioVO();// Tabla Principal
        UsuarioVO usuario = new UsuarioVO();// Tablas Dependientes
        TipoDocumentoVO tipoDocumento = new TipoDocumentoVO();
        TipoServicioVO tipoServicio = new TipoServicioVO();
        EquipoVO equipo = new EquipoVO();
        MarcaVO marcaEquipo = new MarcaVO();
        EstadoVO estado = new EstadoVO();
        Usuario_TipoServicioVO usuario_tiposer = new Usuario_TipoServicioVO();
        UsuarioVO funcionario = new UsuarioVO();
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            //Ciclo que permite listar la informacion a partir de la consulta
            while (resultado.next()) {
                servicio.setIdServicio(resultado.getInt("idServicio"));
                servicio.setFechaSolicitud(resultado.getDate("fechaSolicitud"));
                tipoServicio.setNomTipoServicio(resultado.getString("nomTipoServicio"));
                tipoDocumento.setNombreTipoDoc(resultado.getString("numIdentidad"));
                usuario.setNumeroIdentificacion(resultado.getLong("numIdentidad"));
                usuario.setNombre(resultado.getString("u.nombre"));//Alias del Usuario Cliente
                usuario.setApellido(resultado.getString("u.apellido"));
                usuario.setTelefono(resultado.getLong("telefono"));
                usuario.setCorreo(resultado.getString("correo"));
                usuario.setDireccion(resultado.getString("direccion"));
                estado.setNombreEstado(resultado.getString("nombreEstado"));
                servicio.setDescripcion(resultado.getString("descripcion"));
                equipo.setTipoEquipo(resultado.getString("tipoEquipo"));
                marcaEquipo.setNombreMarca(resultado.getString("nombreMarca"));
                equipo.setModelo(resultado.getString("modelo"));
                equipo.setNumSerial(resultado.getString("numSerial"));
                // Requerido cuando se usa inner join para unir la informacion de la consulta SQL
                servicio.setIdUsuario(usuario);//inner join usuario u on u.idusuario=s.idusuario
                usuario.setTipoIdentificacion(tipoDocumento);//inner join tipodoc td on td.idtipodoc=u.idtipodoc
                servicio.setIdTipoDelServ(tipoServicio);//inner join tipoServicio tp on tp.idTipoSer=s.idTipoDelServ
                servicio.setIdEquipo(equipo);//inner join equipo e on e.idequipo=s.idequipo
                equipo.setIdmarca(marcaEquipo);//inner join marca m on e.idmarca=m.idmarca
                servicio.setIdEstado(estado);//inner join estado es on es.idestado=s.idestado
                servicio.setIdTipoSer(usuario_tiposer);//inner join usuario_tiposer uts on uts.idusutipo=s.idtiposer
                usuario_tiposer.setIdTipoSer(tipoServicio);//inner join tipoServicio ts on uts.idtiposer=ts.idtiposer
                usuario_tiposer.setIdUsuario(funcionario);//inner join usuario ut on uts.idusuario=ut.idusuario
            }

        } catch (Exception e) {
            System.err.print("DAO Error al consultar el servicio seleccionado. Ticket:" + idServicio + ". Excepcion: " + e);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return servicio;
    }

    //Metodo que permite Solicitar los servicios automaticamente por peticion del cliente
    @Override
    public boolean AgregarServicios(ServicioVO servicio) {
        boolean insertado;
        sql = "call registrarServicio_fechaActual(?,?,?,?)";
        try {
            // idUsuario, idTipoDelServ, Descripcion, idEquipo                   
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Atributos que permito Registrar en el procedimiento almacenado
            sentencia.setObject(1, servicio.getIdUsuario().getIdUsuario());
            sentencia.setObject(2, servicio.getIdTipoDelServ().getIdTipoSer());
            sentencia.setObject(3, servicio.getDescripcion());
            sentencia.setObject(4, servicio.getIdEquipo().getIdRegistro());
            sentencia.executeUpdate();
            insertado = true;
        } catch (Exception c) {
            insertado = false;
            System.err.print("DAO Error al ingresar la solicitud de Servicios en el cliente. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }

    //Metodo que permite solicitar los servicios por fecha personalizada por el cliente
    @Override
    public boolean AgregarServiciosFecha(ServicioVO servicio) {
        boolean insertado;
        sql = "call registrarServicioManual(?,?,?,?)";
        try {
            // idUsuario, idTipoDelServ, Descripcion, idEquipo                   
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Atributos que permito Registrar en el procedimiento almacenado
            sentencia.setObject(1, servicio.getIdUsuario().getIdUsuario());
            sentencia.setObject(2, servicio.getFechaSolicitud());
            sentencia.setObject(3, servicio.getIdTipoDelServ().getIdTipoSer());
            sentencia.setObject(4, servicio.getDescripcion());
            sentencia.setObject(5, servicio.getIdEquipo().getIdRegistro());
            sentencia.executeUpdate();
            insertado = true;
        } catch (Exception c) {
            insertado = false;
            System.err.print("DAO Error al ingresar la solicitud de Servicios en el cliente. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }
    
    @Override
    public boolean AgregarServiciosAdmin(ServicioVO servicio) {
        boolean insertado;
        sql = "insert into Servicio(fechaSolicitud, idTipoDelServ, idUsuario, idEquipo, descripcion, idEstado, idtiposer) values(?,?,?,?,?,1,?)";
        try {
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            //Atributos que permito Registrar el servicio solicitado
            sentencia.setObject(1, servicio.getFechaSolicitud());
            sentencia.setObject(2, servicio.getIdTipoDelServ());
            sentencia.setObject(3, servicio.getIdUsuario());
            sentencia.setObject(4, servicio.getIdEquipo());
            sentencia.setObject(5, servicio.getDescripcion());
            sentencia.setObject(6, servicio.getIdTipoSer());
            sentencia.executeUpdate();
            insertado = true;
        } catch (Exception c) {
            insertado = false;
            System.err.print("DAO Error al ingresar la solicitud de Servicios desde el perfil Administrador. Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return insertado;
    }
    @Override
    public boolean AsignarServiciosTecnico(ServicioVO servicio){
        boolean asignado;
        sql="call asignarServicioTecnico(?,?);";
        try{
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.setObject(1, servicio.getIdTipoSer().getIdUsuTipo());
            sentencia.setObject(2, servicio.getIdServicio());
            
            sentencia.executeUpdate();
            asignado=true;
        }catch(Exception e){
            asignado=false;
            System.err.print("DAO Error al ingresar la solicitud de Servicios desde el perfil Administrador. Excepcion: " + e);           
        }finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return asignado;
    }
    @Override
    public boolean ModificarServicios(ServicioVO servicio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean CancelarServicio(int id) {
        boolean cancelado;
        try {
            sql = "call CancelarServicio(" + id + ")";
            conexion = claseConexion.obtenerConexion();
            sentencia = conexion.prepareStatement(sql);
            sentencia.executeUpdate();
            cancelado = true;
        } catch (Exception c) {
            cancelado = false;
            System.err.print("DAO Error al cancelar el servicio seleccionado" + id + " . Excepcion: " + c);
        } finally {
            Conexion.cerrar(sentencia, resultado);
        }
        return cancelado;
    }

}
