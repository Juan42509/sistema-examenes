
package com.sistemas.examanes.controller;

import com.sistemas.examanes.entidades.Examen;
import com.sistemas.examanes.entidades.Pregunta;
import com.sistemas.examanes.servicios.ExamenService;
import com.sistemas.examanes.servicios.PreguntaService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pregunta")
@CrossOrigin("*")
public class PreguntaController {
    
    @Autowired
    private PreguntaService preguntaService;
    
    @Autowired
    private ExamenService examenService;
    
    @PostMapping("/")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }
    
    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }
    
    @GetMapping("/examen/{examenId}")
    public ResponseEntity<?> listarPreguntasExamen(@PathVariable("examenId") Long examenId){
        Examen examen = examenService.obtenerExamen(examenId);
        Set<Pregunta> preguntas = examen.getPreguntas();
        
        List examenes = new ArrayList(preguntas);
        if(examenes.size() > Integer.parseInt(examen.getNumeroDePreguntas())){
            examenes = examenes.subList(0,Integer.parseInt(examen.getNumeroDePreguntas() + 1));
        }
        
        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes);
    }
    
    @GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId){
        return preguntaService.obtenerPregunta(preguntaId);
    }
    
    @DeleteMapping("/{preguntaId}")
    public void elimiarPregunta(@PathVariable("preguntaId") Long preguntaId){
        preguntaService.eliminarPregunta(preguntaId);
    }
    
    @GetMapping("/examen/todos/{examenId}")
    public ResponseEntity<?> listarPreguntasDelExamenComoAdministrador(@PathVariable("examenId") Long examenId){
        Examen examen = new Examen();
        examen.setExamenId(examenId);
        Set<Pregunta> preguntas = preguntaService.obtenerPreguntasExamen(examen);
        return ResponseEntity.ok(preguntas);
    }
    
    @PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<Pregunta> preguntas){
        double puntosMaximos = 0;
        Integer respuestasCorrectas = 0;
        Integer intentos = 0;
        for(Pregunta p: preguntas){
            Pregunta pregunta = preguntaService.listarPregunta(p.getPreguntaId());
            if(pregunta.getRespuesta().equals(p.getRespuestaUsu())){
                respuestasCorrectas ++;
                double puntos = Double.parseDouble(preguntas.get(0).getExamen().getPuntosMaximos())/preguntas.size();
                puntosMaximos += puntos;
            }
            if(p.getRespuestaUsu() != null){
                intentos ++;
            }
        }
        Map<String,Object> respuestas = new HashMap<>();
        respuestas.put("puntosMaximos", puntosMaximos);
        respuestas.put("respuestasCorrectas", respuestasCorrectas);
        respuestas.put("intentos", intentos);
        return ResponseEntity.ok(respuestas);
    }
}
