import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Clientes } from '../../../Entidades/Clientes';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar-cliente',
  imports: [FormsModule],
  templateUrl: './editar-cliente.component.html',
  styleUrl: './editar-cliente.component.css'
})
export class EditarClienteComponent implements OnInit {

  ngOnInit(): void {
    this.buscarCliente();    
  }
  //INYECCIÓN DEPENDENDENCIAS
  constructor(private service: WsService, private router: Router){}

  //VARIABLES Y ARREGLOS
  cliente: Clientes = new Clientes;
  mensajeError: string='';

  //MÉTODOS
  buscarCliente(){ 
    const id = Number(sessionStorage.getItem('id'));
    this.cliente.idCliente=id;

    this.service.buscarCliente(id).subscribe({
      next: data=>{
        this.cliente=data;

      }, error: err=>{
        Swal.fire({
          text: "Ocurrió un error!",
          icon: "error"
        });
      }

    });

  }
  editarCliente(){
    this.mensajeError='';//Limpiar buffer
    this.service.editarCliente(this.cliente).subscribe({
      next: data=>{
        Swal.fire({
          title: "OK!",
          text: "Se editó corréctamente",
          icon: "success",
          timer: 2000,
          showConfirmButton: false
        });
        this.router.navigate(['listarClientes']);

      }, error: err=>{
        this.mensajeError=`Ocurrió un error!<br><br>ERROR: ${err.error}`;
        Swal.fire({
          html:this.mensajeError,
          icon: "error"
        });
      }

    });

  }

}
