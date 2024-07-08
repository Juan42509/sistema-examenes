import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PreguntaService } from 'src/app/services/pregunta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  examenId:any
  preguntas:any
  puntosConseguidos = 0
  respuestasCorrectas = 0
  intentos = 0

  Enviado = false
  timer:any

  constructor(private locarionSt:LocationStrategy, private route:ActivatedRoute, private preguntaService:PreguntaService) { }

  ngOnInit(): void {
    this.examenId =  this.route.snapshot.params['examenId']
    this.prevenirElBotonDeRetroceso();
    this.cargarPreguntas();
  }

  cargarPreguntas(){
    this.preguntaService.listarPreguntasDelExamenParaLaPrueba(this.examenId).subscribe(
      (data)=>{
        this.preguntas = data
        
        this.timer = this.preguntas.length *2 * 60;

        this.preguntas.forEach((pregunta:any) => {
          pregunta['respuestaUsu'] = ''
          // console.log(this.preguntas);
        });
        this.iniciarTemporizador()
      },(error)=>{
        Swal.fire('Error','Error al cargar las preguntas de la prueba','error')
      }
    )
  }

  iniciarTemporizador(){
    let t = window.setInterval(()=>{
      if(this.timer <= 0){
        this.evaluarExamen()
        clearInterval(t);
      }else{
        this.timer--
      }
    }, 1000)
  }

  prevenirElBotonDeRetroceso(){
    history.pushState(null,null!,location.href)
    this.locarionSt.onPopState(()=>{
      history.pushState(null,null!,location.href)
    })
  }

  enviarCuestionario(){
    Swal.fire({
      title:'¿Quieres enviar el examen?',
      showCancelButton: true,
      confirmButtonText:'Enviar',
      cancelButtonText:'Cancelar',
      icon:'info'
    }).then((result) => {
      if(result.isConfirmed){
        this.evaluarExamen();
      }
    })
  }

  evaluarExamen(){
    // this.Enviado = true
    //     this.preguntas.forEach((pregunta:any) => {
    //       if(pregunta.respuestaUsu == pregunta.respuesta){
    //         this.respuestasCorrectas++;
    //         let puntos = this.preguntas[0].examen.puntosMaximos/this.preguntas.length
    //         this.puntosConseguidos += puntos
    //       }
    //       if(pregunta.respuestaUsu.trim() != ''){
    //         this.intentos++
    //       }
    //     });
    //     console.log('rc: '+this.respuestasCorrectas);
    //     console.log('pc:' + this.puntosConseguidos);
    //     console.log('i: ' + this.intentos);
    //     console.log(this.preguntas);
    this.preguntaService.evaluarExamen(this.preguntas).subscribe(
      (data:any) =>{
        this.puntosConseguidos = data.puntosMaximos;
        this.respuestasCorrectas = data.respuestasCorrectas
        this.intentos = data.intentos
        this.Enviado = true
      },(error) =>{
        console.log(error);
        
      }
    )
  }

  obtenerHoraFormateada(){
    let mm = Math.floor(this.timer/60)
    let ss = this.timer - mm*60
    return `${mm} : min : ${ss} seg`
  }

  imprimirPagina(){
    window.print();
  }

}
