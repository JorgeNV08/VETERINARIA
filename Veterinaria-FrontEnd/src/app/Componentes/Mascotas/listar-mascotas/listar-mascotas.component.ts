import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Mascotas } from '../../../Entidades/Mascotas';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-listar-mascotas',
  imports: [FormsModule],
  templateUrl: './listar-mascotas.component.html',
  styleUrl: './listar-mascotas.component.css'
})
export class ListarMascotasComponent implements OnInit{

  
  ngOnInit(): void {
    this.listarMascotas();
    this.service.listarMascotasConDatos().subscribe(data=>{
      this.mascotas = data;
    })
  }

  //Inyeccion dependencias
  constructor(private service: WsService, private router: Router){}
  
  //Variables y Arreglos
  mascota: Mascotas = new Mascotas;
  //mascotas!: Mascotas[];
  mascotas: any[] = [];

  //Metodos
  listarMascotas(){
    this.service.listarMascotas().subscribe({
      next: data=>{
        this.mascotas = data;

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

  editarMascota(mascota: Mascotas){
    Number(sessionStorage.setItem('id', mascota.idMascota.toString()));
    this.router.navigate(['editarMascota']);
  }

  eliminarMascota(idMascota: number){
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
                    this.service.eliminarMascota(idMascota).subscribe({
                      next: data=>{
                        this.ngOnInit();
                        swalWithBootstrapButtons.fire({
                          title: "Eliminado!",
                          text: "El registro ha sido eliminado.",
                          icon: "success",
                          showConfirmButton: false,
                          timer: 1500
                        });
                      }, error: err=>{
                        console.error(JSON.stringify(err.error.message));
                        swalWithBootstrapButtons.fire({
                          title: "Error",
                          text: "Ocurrio un error al eliminar la nformación :)",
                          icon: "error",
                        });
                      }

                    });

                  } else if (result.dismiss === Swal.DismissReason.cancel)
                  {
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
