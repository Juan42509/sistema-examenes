package com.sistemas.examanes.servicios;

import com.sistemas.examanes.entidades.Examen;
import com.sistemas.examanes.entidades.Pregunta;
import java.util.Set;


public interface PreguntaService {
    
    Pregunta agregarPregunta(Pregunta pregunta);
    Pregunta actualizarPregunta(Pregunta pregunta);
    Set<Pregunta> obtenerPreguntas();
    Pregunta obtenerPregunta(Long preguntaId);
    Set<Pregunta> obtenerPreguntasExamen(Examen examen);
    void eliminarPregunta(Long preguntaId);
    Pregunta listarPregunta(Long preguntaId);
    
}
