import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Clientes } from '../../../Entidades/Clientes';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-cliente',
  imports: [FormsModule],
  templateUrl: './listar-cliente.component.html',
  styleUrl: './listar-cliente.component.css'
})
export class ListarClienteComponent implements OnInit {

  ngOnInit(): void {
    this.listarClientes();
  }

  //Inyección dependencias
  constructor(private service: WsService, private router: Router ){}


  //Variables y arreglos
  cliente: Clientes = new Clientes;
  clientes!: Clientes[];

  //Métodos
  listarClientes(){
    this.service.listarClientes().subscribe({

      next: data=>{
        this.clientes = data;

      }, error: err =>{
        console.error('Ocurrió un error al obtener la info: ' + JSON.stringify(err.error.message));
        Swal.fire({
          icon: "error",
          title: "ERROR!",
          text: "Ocurrió un error al obtener la información!"
        });

      }

    });
  }


  editarCliente(cliente: Clientes){
    Number(sessionStorage.setItem('id', cliente.idCliente.toString()));
    this.router.navigate(['editarCliente']);
  }
  
  eliminarCliente(idCliente: number){
    const swalWithBootstrapButtons = Swal.mixin({
          customClass: {
            confirmButton: "btn btn-success",
            cancelButton: "btn btn-danger"
          },
          buttonsStyling: false
        });
            swalWithBootstrapButtons.fire({
              title: "Estas seguro?",
              text: "No se podrá revertir la elimnacion!",
              icon: "warning",
              showCancelButton: true,
              confirmButtonText: "Si eliminalo!",
              cancelButtonText: "No, Cancelar!",
              reverseButtons: true
            }).then((result) => {
              if(result.isConfirmed){
                this.service.eliminarCliente(idCliente).subscribe({
                  next: data =>{
                    this.ngOnInit();
                    swalWithBootstrapButtons.fire({
                      title: "Eliminado!",
                      text: "El registro ha sido eliminado.",
                      icon: "success",
                      showConfirmButton: false,
                      timer: 1500
                    });

                  }, error: err =>{
                    console.error(JSON.stringify(err.error.message));
                    swalWithBootstrapButtons.fire({
                      title: "Error",
                      text: "Ocurrio un error al eliminar la nformación :)",
                      icon: "error",
                    });
                  }

                });

              } else if (result.dismiss === Swal.DismissReason.cancel){
                swalWithBootstrapButtons.fire({
                  title: "Cancelado",
                  text: "Operacion cancelada :)",
                  icon: "error",
                  showConfirmButton: false,
                  timer: 1500
                });
              }



            });
  }
}
