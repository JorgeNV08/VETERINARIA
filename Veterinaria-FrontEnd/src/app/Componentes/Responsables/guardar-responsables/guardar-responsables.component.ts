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

  //Metodos
  guardarResponsable(){
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
