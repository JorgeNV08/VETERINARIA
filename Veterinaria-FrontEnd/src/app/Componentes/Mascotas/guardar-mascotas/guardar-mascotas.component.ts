import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Mascotas } from '../../../Entidades/Mascotas';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar-mascotas',
  imports: [FormsModule],
  templateUrl: './guardar-mascotas.component.html',
  styleUrl: './guardar-mascotas.component.css'
})
export class GuardarMascotasComponent implements OnInit {

  ngOnInit(): void {
    
  }

  //Inyeccion dependencias
  constructor(private service: WsService, private router: Router){}

  //Variables y Arreglos
  mascota: Mascotas = new Mascotas;

  //Métodos
  guardarMascota(){
    this.service.guardarMascota(this.mascota).subscribe({
      next: data=>{
        Swal.fire({
          title: "OK!",
          icon: "success",
          text: "Se guardó exitósamente el cliente.",
          timer: 2000,
          showConfirmButton: false
        });
        this.router.navigate(['listarMascotas']);
      }, error: err=>{
        console.error(JSON.stringify(err));
        Swal.fire({
          title: "ERROR!",
          icon: "error",
          text: "Ocurrió un error al guardar el cliente ",
          confirmButtonText: "Continuar"
        });
      }

    });
  }


}
