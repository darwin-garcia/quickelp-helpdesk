/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;
import com.quickelp.programa.persistencia.vo.TipoDocumentoVO;
import java.util.List;
/**
 *
 * @author Darwin Garcia
 */
public interface InterfazTipoDoc {
    public List listadoTipoDoc();
    public TipoDocumentoVO ConsultarTipoDoc(int id);
    public abstract boolean AgregarTipoDoc(TipoDocumentoVO tipoDocumento);
    public abstract boolean ModificarTipoDoc(TipoDocumentoVO tipoDocumento);
    public abstract boolean EliminarTipoDoc(int id);
    
}
