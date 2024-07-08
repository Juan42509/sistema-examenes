
package com.sistemas.examanes.servicios;

import com.sistemas.examanes.entidades.Categoria;
import com.sistemas.examanes.entidades.Examen;
import java.util.List;
import java.util.Set;


public interface ExamenService {
    
    Examen agregarExamen(Examen examen);
    Examen actualizarExamen(Examen examen);
    Set<Examen> obtenerExamenes();
    Examen obtenerExamen(Long examenId);
    void eliminarExamen(Long examenId);
    List<Examen> listarExamenesDeUnaCategoria(Categoria categoria);
    List<Examen> obtenerExamenesActivo();
    List<Examen> obtenerExamenActivosDeUnaCategoria(Categoria categoria);
    
}
