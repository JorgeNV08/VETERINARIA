import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Responsables } from '../../../Entidades/Responsables';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar-responsables',
  imports: [FormsModule],
  templateUrl: './editar-responsables.component.html',
  styleUrl: './editar-responsables.component.css'
})
export class EditarResponsablesComponent implements OnInit{

  ngOnInit(): void {
    this.buscarResponsable();
  }

  //Inyeccion dependencias
  constructor(private service: WsService, private router: Router){}
  
  //Variables
  responsable: Responsables = new Responsables;
  mensajeError: string='';

  //Métodos
  buscarResponsable(){
    const id = Number(sessionStorage.getItem('id'));
    this.responsable.idResponsable = id;
    this.service.buscarResponsable(id).subscribe({
      next: data=>{
        this.responsable=data;
      }, error: err=>{
        Swal.fire({
          text: "Ocurrió un error!",
          icon: "error"
        });
      }
  

    });

  }
  editarResponsable(){
    this.mensajeError=''; //Limiamos el buffer
    this.service.editarResponsable(this.responsable).subscribe({
      next: data=>{
        Swal.fire({
          title: "OK!",
          text: "Se editó corréctamente",
          icon: "success",
          timer: 2000,
          showConfirmButton: false
        });
        this.router.navigate(['listarResponsables']);
      }, error: err=>{
        this.mensajeError=`Ocurrió un error!<br><br>ERROR: ${err.error}`;
        Swal.fire({
          html: this.mensajeError,
          icon: "error"
        });
      }

    });
  }












}
