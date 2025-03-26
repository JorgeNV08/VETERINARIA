import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Veterinaria } from '../../../Entidades/Veterinaria';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar-veterinaria',
  imports: [FormsModule],
  templateUrl: './guardar-veterinaria.component.html',
  styleUrl: './guardar-veterinaria.component.css'
})
export class GuardarVeterinariaComponent {

  //Inyeccion de dependencias
  constructor(private service: WsService, private router: Router){}

  //Variables
  veterinaria: Veterinaria = new Veterinaria;

  //Métodos
  guardar(){
    this.service.guardarVeterinaria(this.veterinaria).subscribe({
      next: data=>{
        Swal.fire({
          title: "OK!",
          icon: "success",
          text: "Se guardó exitósamente la Veterinaria.",
          timer: 2000,
          showConfirmButton: false
        });
        this.router.navigate(['listarVeterinarias']);
      }, error: err=>{
        console.error(JSON.stringify(err));
        Swal.fire({
          title: "ERROR!",
          icon: "error",
          text: "Ocurrió un error al guardar la Veterinaria " + this.veterinaria.nombre,
          confirmButtonText: "Continuar"
        });

      }

    });

  }
}
