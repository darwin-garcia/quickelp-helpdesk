/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quickelp.programa.interfaz;

import com.quickelp.programa.persistencia.vo.DiagnosticoVO;
import java.util.List;

/**
 *
 * @author Darwin Garcia
 */
public interface InterfazDiagnostico {
    public List listadoDiagnosticos();
    public List consultarDiagnosticos(int id);
    public abstract boolean AgregarDiagnostico(DiagnosticoVO diagnostico);
    public abstract boolean ModificarDiagnostico(DiagnosticoVO diagnostico);
    
}
