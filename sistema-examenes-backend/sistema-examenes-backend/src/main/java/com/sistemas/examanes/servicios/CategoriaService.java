/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistemas.examanes.servicios;

import com.sistemas.examanes.entidades.Categoria;
import java.util.Set;

/**
 *
 * @author juanf
 */
public interface CategoriaService {
    
    Categoria agregarCategoria(Categoria categoria);
    Categoria actualizarCategoria(Categoria categoria);
    Set<Categoria> obtenerCategorias();
    Categoria obtenerCategoria(Long categoriaId);
    void eliminarCategoria(Long categoriaId);
    
}
