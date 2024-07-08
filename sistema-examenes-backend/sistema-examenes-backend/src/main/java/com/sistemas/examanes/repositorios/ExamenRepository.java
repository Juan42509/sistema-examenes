package com.sistemas.examanes.repositorios;

import com.sistemas.examanes.entidades.Categoria;
import com.sistemas.examanes.entidades.Examen;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExamenRepository extends JpaRepository<Examen, Long>{
    List<Examen> findByCategoria(Categoria categoria);
    List<Examen> findByActivo(Boolean estado);
    List<Examen> findByCategoriaAndActivo(Categoria categoria,Boolean estado);
}
