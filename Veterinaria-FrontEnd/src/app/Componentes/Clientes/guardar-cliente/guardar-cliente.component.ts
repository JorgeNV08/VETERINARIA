import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Clientes } from '../../../Entidades/Clientes';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-guardar-cliente',
  imports: [FormsModule],
  templateUrl: './guardar-cliente.component.html',
  styleUrl: './guardar-cliente.component.css'
})
export class GuardarClienteComponent implements OnInit {

  ngOnInit(): void {
    
  }

  //INYECCION
  constructor(private service: WsService, private router: Router){}

  //VARIABLES Y ARREGLOS
  cliente: Clientes = new Clientes;
  mensajeError: string = '';

  //METODOS
  guardarCliente(){
    this.mensajeError=''; //Limpiar buffer
    this.service.guardarCliente(this.cliente).subscribe({
      next: data=>{
        Swal.fire({
          title: "OK!",
          icon: "success",
          text: "Se guardó exitósamente el cliente.",
          timer: 2000,
          showConfirmButton: false
        });
        this.router.navigate(['listarClientes']);
      }, error: err=>{
        console.error(JSON.stringify(err));
        this.mensajeError = `Ocurrió un error al guardar el cliente.<br><br>ERROR: ${err.error}`;

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
