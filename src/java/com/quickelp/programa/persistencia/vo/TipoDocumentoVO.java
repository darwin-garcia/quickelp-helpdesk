/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.persistencia.vo;

/**
 *
 * @author Darwin Garcia
 */
public class TipoDocumentoVO {
    private Integer idTipoDocumento;
    private String nombreTipoDoc;

    public TipoDocumentoVO() {
    }

    public TipoDocumentoVO(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public TipoDocumentoVO(Integer idTipoDocumento, String nombreTipoDoc) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombreTipoDoc = nombreTipoDoc;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombreTipoDoc() {
        return nombreTipoDoc;
    }

    public void setNombreTipoDoc(String nombreTipoDoc) {
        this.nombreTipoDoc = nombreTipoDoc;
    }

    @Override
    public String toString() {
        return "TipoDocumentoVO{" + "idTipoDocumento=" + idTipoDocumento + ", nombreTipoDoc=" + nombreTipoDoc + '}';
    }

    
    
}
