/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;

import com.quickelp.programa.persistencia.vo.MarcaVO;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public interface InterfazMarcaPC {
    public List listadoMarca();
    public MarcaVO consultarMarca(int id);
    public abstract boolean AgregarMarca(MarcaVO marca);
    public abstract boolean EliminarMarca(int id);
}
