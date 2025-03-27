import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Responsables } from '../../../Entidades/Responsables';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-responsables',
  imports: [FormsModule],
  templateUrl: './listar-responsables.component.html',
  styleUrl: './listar-responsables.component.css'
})
export class ListarResponsablesComponent implements OnInit {

  ngOnInit(): void {
    //this.listarResponsables();
    this.service.listarResponsablesConVeterinaria().subscribe(data=>{
      this.responsables = data;
    })
  }

  //Inyección dependencias
  constructor(private service: WsService, private router: Router){}

  //Variables y arreglos
  responsable: Responsables = new Responsables;
  //responsables!: Responsables[];
  responsables: any[] = [];

  //Métodos
  listarResponsables(){
    this.service.listarResponsables().subscribe({
      next: data =>{
        this.responsables = data;
      }, error: err=>{
        console.error('Ocurrió un error al obtener la info: ' + JSON.stringify(err.error.message));
        Swal.fire({
          icon: "error",
          title: "ERROR!",
          text: "Ocurrió un error al obtener la información!"
        });
      }

    });
  }

  editarResponsable(responsable: Responsables){
    Number(sessionStorage.setItem('id', responsable.idResponsable.toString()));
    this.router.navigate(['editarResponsable']);
  }

  eliminarResponsable(idResponsable: number){
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
              this.service.eliminarResponsable(idResponsable).subscribe({
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
