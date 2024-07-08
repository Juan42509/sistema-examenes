package com.sistemas.examanes.repositorios;

import com.sistemas.examanes.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaRepositoty extends JpaRepository<Categoria, Long>{
    
}
