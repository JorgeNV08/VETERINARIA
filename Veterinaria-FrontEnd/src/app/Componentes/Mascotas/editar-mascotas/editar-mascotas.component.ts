import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Mascotas } from '../../../Entidades/Mascotas';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-editar-mascotas',
  imports: [FormsModule],
  templateUrl: './editar-mascotas.component.html',
  styleUrl: './editar-mascotas.component.css'
})
export class EditarMascotasComponent implements OnInit {

  ngOnInit(): void {
    this.buscarMascota();
  }
  //Inyeccion de dependencias
  constructor(private service: WsService, private router: Router){}

  //Variables y Arreglos
  mascota: Mascotas = new Mascotas;
  mensajeError: string='';

  //Métodos
  buscarMascota(){
    const id = Number(sessionStorage.getItem('id'));
    this.mascota.idMascota = id;

    this.service.buscarMascota(id).subscribe({
      next: data=>{
        this.mascota=data;

      }, error: err=>{
         Swal.fire({
            text: "Ocurrió un error!",
            icon: "error"
          });
      }
    });

  }

  editarMascota(){
    this.mensajeError=''; //Limpiamos buffer
    this.service.editarMascota(this.mascota).subscribe({
      next: data=>{
        Swal.fire({
          title: "OK!",
          text: "Se editó corréctamente",
          icon: "success",
          timer: 2000,
          showConfirmButton: false
        });
        this.router.navigate(['listarMascotas']);

      }, error: err=>{
        this.mensajeError=`Ocurrió un error!<br><br>Error: ${err.error}`
        Swal.fire({
          html: this.mensajeError,
          icon: "error"
        });
      }
    });
  }
} 
