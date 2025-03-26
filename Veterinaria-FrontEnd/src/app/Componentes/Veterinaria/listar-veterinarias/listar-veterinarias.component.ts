import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Veterinaria } from '../../../Entidades/Veterinaria';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-veterinarias',
  imports: [CommonModule, FormsModule],
  templateUrl: './listar-veterinarias.component.html',
  styleUrl: './listar-veterinarias.component.css'
})
export class ListarVeterinariasComponent implements OnInit{

  //Inyeccion de dependencias
  constructor(private service: WsService, private router: Router){}

  ngOnInit(): void {
    this.listarVeterinarias();
  }

  //Variables y arreglos
  veterinarias!: Veterinaria[];
  veterinaria: Veterinaria = new Veterinaria;
  idVeterinaria!: number;


  listarVeterinarias(){
    this.service.listarVeterinarias().subscribe({
      next: data =>{
        console.table(data);
        this.veterinarias = data;
      },
      error: err =>{
        console.error('Ocurrió un error al obtener la info: ' + JSON.stringify(err.error.message));
        //alert('Ocurrio un error al consultar la información: ' + err);

        Swal.fire({
          icon: "error",
          title: "ERROR!",
          text: "Ocurrió un error al obtener la información!",
          //footer: '<a href="#">Why do I have this issue?</a>'
        });
      }
    });
  }

  editarVeterinaria(veterinaria: Veterinaria){
    Number(sessionStorage.setItem('id',veterinaria.idVeterinaria.toString()));
    this.router.navigate(['editarVeterinaria'])
  }

  eliminarVeterinaria(idVeterinaria: number){
    
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
            this.service.eliminarVeterinaria(idVeterinaria).subscribe({
              next: data => {
                this.ngOnInit();
                swalWithBootstrapButtons.fire({
                  title: "Eliminado!",
                  text: "El registro ha sido eliminado.",
                  icon: "success",
                  showConfirmButton: false,
                  timer: 1500
                });
              }, error: err => {
                console.error(JSON.stringify(err.error.message));
                swalWithBootstrapButtons.fire({
                  title: "Error",
                  text: "Ocurrio un error al eliminar la nformación :)",
                  icon: "error",
                });
              }

            });

          } else if(result.dismiss === Swal.DismissReason.cancel){
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
