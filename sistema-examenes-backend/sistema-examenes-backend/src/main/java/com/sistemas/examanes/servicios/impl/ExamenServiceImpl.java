
package com.sistemas.examanes.servicios.impl;

import com.sistemas.examanes.entidades.Categoria;
import com.sistemas.examanes.entidades.Examen;
import com.sistemas.examanes.repositorios.ExamenRepository;
import com.sistemas.examanes.servicios.ExamenService;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamenServiceImpl implements ExamenService{
    
    @Autowired
    private ExamenRepository examenRepository;

    @Override
    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Examen actualizarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    @Override
    public Set<Examen> obtenerExamenes() {
        return new LinkedHashSet<>(examenRepository.findAll());
    }

    @Override
    public Examen obtenerExamen(Long examenId) {
        return examenRepository.findById(examenId).get();
    }

    @Override
    public void eliminarExamen(Long examenId) {
        Examen examen = new Examen();
        examen.setExamenId(examenId);
        examenRepository.delete(examen);
    }

    @Override
    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return examenRepository.findByCategoria(categoria);
    }

    @Override
    public List<Examen> obtenerExamenesActivo() {
        return examenRepository.findByActivo(true);
    }

    @Override
    public List<Examen> obtenerExamenActivosDeUnaCategoria(Categoria categoria) {
        return examenRepository.findByCategoriaAndActivo(categoria, true);
    }
    
}
