import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PreguntaService } from 'src/app/services/pregunta.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-actualizar-pregunta',
  templateUrl: './actualizar-pregunta.component.html',
  styleUrls: ['./actualizar-pregunta.component.css']
})
export class ActualizarPreguntaComponent implements OnInit {

  preguntaId: any
  pregunta:any
  examen:any

  constructor(private route:ActivatedRoute, private preguntaService:PreguntaService, private router:Router) { }

  ngOnInit(): void {
    this.preguntaId = this.route.snapshot.params['preguntaId']
    this.preguntaService.obtenerPregunta(this.preguntaId).subscribe(
      (data:any)=>{
        this.pregunta = data
        console.log(this.pregunta);
      },(error)=>{
        console.log(error);
      }
    )
  }

  public actualizarDatosPregunta(){
    this.preguntaService.actualizarPregunta(this.pregunta).subscribe(
      (data) =>{
        Swal.fire('Pregunta Actualizada','Pregunta actualizada con exito','success').then(
          (e)=>{
            this.router.navigate(['/admin/ver-preguntas/'+this.pregunta.examen.examenId+'/'+this.pregunta.examen.titulo])
          })
      }
    )
  }

}
