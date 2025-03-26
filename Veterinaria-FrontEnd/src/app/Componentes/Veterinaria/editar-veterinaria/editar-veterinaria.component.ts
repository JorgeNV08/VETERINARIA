import { Component, OnInit } from '@angular/core';
import { WsService } from '../../../Service/ws.service';
import { Router } from '@angular/router';
import { Veterinaria } from '../../../Entidades/Veterinaria';
import { FormsModule } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-editar-veterinaria',
  imports: [FormsModule],
  templateUrl: './editar-veterinaria.component.html',
  styleUrl: './editar-veterinaria.component.css'
})
export class EditarVeterinariaComponent implements OnInit{

  constructor(private service: WsService, private router: Router){}

  ngOnInit(): void {
    this.buscarVeterinaria();
  }

  veterinaria: Veterinaria = new Veterinaria;

  buscarVeterinaria(){
    const id = Number(sessionStorage.getItem('id'));
    this.veterinaria.idVeterinaria = id;

    this.service.buscarVeterinaria(this.veterinaria.idVeterinaria).subscribe({
      next: data =>{
        this.veterinaria = data;
      }, error: err =>{
        Swal.fire({
          text: "Ocurrió un error!",
          icon: "error"
        });
      }

    });
  }

  editar(){
    this.service.editarVeterinaria(this.veterinaria).subscribe(
      data=>{
        Swal.fire({
          title: "OK!",
          text: "Se editó corréctamente",
          icon: "success",
          timer: 2000,
          showConfirmButton: false
        });
        this.router.navigate(['listarVeterinarias']);
      }

    );
  }


}
