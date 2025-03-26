import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Clientes } from '../Entidades/Clientes';
import { Mascotas } from '../Entidades/Mascotas';
import { Responsables } from '../Entidades/Responsables';
import { Veterinaria } from '../Entidades/Veterinaria';
import { forkJoin, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WsService {

  //URLs del BackEnd
  urlClientes = 'http://localhost:8000/Clientes';
  urlMascotas = 'http://localhost:8000/Mascotas';
  urlResponsables = 'http://localhost:8000/Responsable';
  urlVeterinaria = 'http://localhost:8000/Veterinaria';

  // Credenciales para BasicAuth
  private username = 'jorge';
  private password = '54321';

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${this.username}:${this.password}`)
    });
  }

  //Inyectamos la dependencia HttpCliente para enviar peticiones al backEnd
  constructor(private http: HttpClient) {}

  //*************************** C L I E N T E S  ***************************/

  listarClientes(){
    return this.http.get<Clientes[]>(`${this.urlClientes}`, { headers: this.getHeaders() });
  }

  buscarCliente(idCliente: number) {
    return this.http.get<Clientes>(`${this.urlClientes}/${idCliente}`, { headers: this.getHeaders() });
  }
  

  guardarCliente(cliente: Clientes){
    return this.http.post<Clientes>(`${this.urlClientes}`, cliente, {headers: this.getHeaders()});
  }

  editarCliente(cliente: Clientes){
    return this.http.put<Clientes>(`${this.urlClientes}`, cliente, {headers: this.getHeaders()});
  }

  eliminarCliente(idCliente: number){
    return this.http.delete<Clientes>(`${this.urlClientes}/${idCliente}`, {headers: this.getHeaders()});
  }

  //*************************** M A S C O T A S  ***************************/

  listarMascotas(){
    return this.http.get<Mascotas[]>(`${this.urlMascotas}`, {headers: this.getHeaders()});
  }

  buscarMascota(idMascota: number){
    return this.http.get<Mascotas>(`${this.urlMascotas}/${idMascota}`, {headers: this.getHeaders()});
  }

  guardarMascota(mascota: Mascotas){
    return this.http.post<Mascotas>(`${this.urlMascotas}`, mascota, {headers: this.getHeaders()});
  }
  
  editarMascota(mascota: Mascotas){
    return this.http.put<Mascotas>(`${this.urlMascotas}`, mascota, {headers: this.getHeaders()});
  }

  eliminarMascota(idMascota: number){
    return this.http.delete<Mascotas>(`${this.urlMascotas}/${idMascota}`, {headers: this.getHeaders()});
  }


  //*************************** R E S P O N S A B L E S  ***************************/

  listarResponsables(){
    return this.http.get<Responsables[]>(`${this.urlResponsables}`, {headers: this.getHeaders()});
  }

  buscarResponsable(idResponsable: number){
    return this.http.get<Responsables>(`${this.urlResponsables}/${idResponsable}`, {headers: this.getHeaders()});
  }

  guardarResponsable(responsable: Responsables){
    return this.http.post<Responsables>(`${this.urlResponsables}`, responsable, {headers: this.getHeaders()});
  }

  editarResponsable(responsable: Responsables){
    return this.http.put<Responsables>(`${this.urlResponsables}`, responsable, {headers: this.getHeaders()});
  }

  eliminarResponsable(idResponsable: number){
    return this.http.delete<Responsables>(`${this.urlResponsables}/${idResponsable}`, {headers: this.getHeaders()});
  }



  //*************************** V E T E R I N A R I A  ***************************/

  listarVeterinarias(){
    return this.http.get<Veterinaria[]>(`${this.urlVeterinaria}`, {headers: this.getHeaders()});
  }

  buscarVeterinaria(idVeterinaria: number){
    return this.http.get<Veterinaria>(`${this.urlVeterinaria}/${idVeterinaria}`, {headers: this.getHeaders()});
  }

  guardarVeterinaria(veterinaria: Veterinaria){
    return this.http.post<Veterinaria>(`${this.urlVeterinaria}`, veterinaria, {headers: this.getHeaders()});
  }

  editarVeterinaria(veterinaria: Veterinaria){
    return this.http.put<Veterinaria>(`${this.urlVeterinaria}`, veterinaria, {headers: this.getHeaders()});
  }

  eliminarVeterinaria(idVeterinaria: number){
    return this.http.delete<Veterinaria>(`${this.urlVeterinaria}/${idVeterinaria}`, {headers: this.getHeaders()});
  }


  //*************************** M E T O D O S   P E R S O N A L I Z A D O S ***************************/

 // Método para listar Mascotas con datos de cliente, veterinaria y responsable
listarMascotasConDatos() {
    return forkJoin({
      mascotas: this.listarMascotas(),
      clientes: this.listarClientes(),
      responsables: this.listarResponsables(),
      veterinarias: this.listarVeterinarias()
    }).pipe(
      map(({ mascotas, clientes, responsables, veterinarias }) => {
        return mascotas.map(mascota => {
          const cliente = clientes.find(c => c.idCliente === mascota.clienteId);
          const responsable = responsables.find(r => r.idResponsable === mascota.responsableId);
          const veterinaria = veterinarias.find(v => v.idVeterinaria === responsable?.veterinariaId);

          return {
            ...mascota,
            nombreCliente: cliente?.nombre || 'Desconocido',
            nombreResponsable: responsable?.nombre || 'Desconocido',
            nombreVeterinaria: veterinaria?.nombre || 'Desconocido'
          };
        });
      })
    );
}

//Método para listar Responsables con datos de veterinaria
listarResponsablesConVeterinaria(){
  return forkJoin({
    responsables: this.listarResponsables(), //RECUPERAMOS TODOS LOS RESPONSABLES Y TODAS LAS VETERINARIAS
    veterinarias: this.listarVeterinarias()
  }).pipe(
    map(({responsables, veterinarias}) =>{
      return responsables.map(responsable=>{
        const veterinaria = veterinarias.find(v => v.idVeterinaria === responsable.veterinariaId);
        return {
          ...responsable,
          nombreVeterinaria: veterinaria?.nombre || 'Desconocido'
        };
      });
    })

  );

}
} 
