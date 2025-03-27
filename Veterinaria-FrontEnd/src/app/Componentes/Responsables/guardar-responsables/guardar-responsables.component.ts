import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Responsables } from '../../../Entidades/Responsables';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar-responsables',
  imports: [FormsModule],
  templateUrl: './guardar-responsables.component.html',
  styleUrl: './guardar-responsables.component.css'
})
export class GuardarResponsablesComponent implements OnInit{

  ngOnInit(): void {
     
  }

  //Inyección dependencias
  constructor(private service: WsService, private router: Router){}

  //Variables
  responsable: Responsables = new Responsables;
  mensajeError: string = '';

  //Metodos
  guardarResponsable(){
    this.mensajeError=''; //Limpiar buffer
    this.service.guardarResponsable(this.responsable).subscribe({
      next: data=>{
        Swal.fire({
          title: "OK!",
          icon: "success",
          text: "Se guardó exitósamente el cliente.",
          timer: 2000,
          showConfirmButton: false
        });
        this.router.navigate(['listarResponsables']);
      }, error: err=>{
        console.error(JSON.stringify(err));
        this.mensajeError = `Ocurrió un error al guardar al responsable.<br><br>ERROR: ${err.error}`;
        Swal.fire({
          title: "ERROR!",
          icon: "error",
          html: this.mensajeError,
          confirmButtonText: "Continuar"
        });
      }

    });
  }
}
