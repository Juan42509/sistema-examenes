package com.sistemas.examanes.repositorios;

import com.sistemas.examanes.entidades.Examen;
import com.sistemas.examanes.entidades.Pregunta;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long>{
    
    Set<Pregunta> findByExamen(Examen examen);
    
}
