import Swal from 'sweetalert2';
import { ExamenService } from './../../../services/examen.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-examenes',
  templateUrl: './view-examenes.component.html',
  styleUrls: ['./view-examenes.component.css']
})
export class ViewExamenesComponent implements OnInit {

  examenes : any = []

  constructor(private examenService:ExamenService) { }

  ngOnInit(): void {
    this.examenService.listarCuestionarios().subscribe(
      (data:any)=>{
        this.examenes = data
        console.log(data);
      },(error)=>{
        console.log(error);
        Swal.fire("Error","Error al cargar los examenes",'error')
      }
    )
  }

  eliminarExamen(examenId:any){
    Swal.fire({
      title:'Eliminar examen',
      text:'¿Estas seguro de eliminar el examen?',
      icon:'warning',
      showCancelButton: true,
      confirmButtonColor:'#3085d6',
      cancelButtonColor:'#d33',
      confirmButtonText:'Eliminar',
      cancelButtonText:'Cancelar'
    }).then((result) => {
      if(result.isConfirmed){
        this.examenService.eliminarExamen(examenId).subscribe(
          (data:any) =>{
            this.examenes = this.examenes.filter((examen:any) => examen.examenId != examenId)
            Swal.fire('Examen eliminado','El examen ha sido eliminado de la base de datos','success')
          },(error) =>{
            console.log(error);
            Swal.fire("Error !!",'Error al eliminar el examen','error')
          }
        )
      }
    })
  }

}
