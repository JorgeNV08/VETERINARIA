import { Routes } from '@angular/router';
import { ListarClienteComponent } from './Componentes/Clientes/listar-cliente/listar-cliente.component';
import { EditarClienteComponent } from './Componentes/Clientes/editar-cliente/editar-cliente.component';
import { GuardarClienteComponent } from './Componentes/Clientes/guardar-cliente/guardar-cliente.component';
import { ListarMascotasComponent } from './Componentes/Mascotas/listar-mascotas/listar-mascotas.component';
import { EditarMascotasComponent } from './Componentes/Mascotas/editar-mascotas/editar-mascotas.component';
import { GuardarMascotasComponent } from './Componentes/Mascotas/guardar-mascotas/guardar-mascotas.component';
import { ListarResponsablesComponent } from './Componentes/Responsables/listar-responsables/listar-responsables.component';
import { EditarResponsablesComponent } from './Componentes/Responsables/editar-responsables/editar-responsables.component';
import { GuardarResponsablesComponent } from './Componentes/Responsables/guardar-responsables/guardar-responsables.component';
import { ListarVeterinariasComponent } from './Componentes/Veterinaria/listar-veterinarias/listar-veterinarias.component';
import { EditarVeterinariaComponent } from './Componentes/Veterinaria/editar-veterinaria/editar-veterinaria.component';
import { GuardarVeterinariaComponent } from './Componentes/Veterinaria/guardar-veterinaria/guardar-veterinaria.component';

export const routes: Routes = [

    //Rutas asociadas con los componentes
    { path: 'listarClientes', component: ListarClienteComponent},
    { path: 'editarCliente', component: EditarClienteComponent},
    { path: 'guardarCliente', component: GuardarClienteComponent},
    
    {path: 'listarMascotas', component: ListarMascotasComponent},
    {path: 'editarMascota', component: EditarMascotasComponent},
    {path: 'guardarMascota', component: GuardarMascotasComponent},

    {path: 'listarResponsables', component: ListarResponsablesComponent},
    {path: 'editarResponsable', component: EditarResponsablesComponent},
    {path: 'guardarResponsable', component: GuardarResponsablesComponent},

    {path: 'listarVeterinarias', component: ListarVeterinariasComponent},
    {path: 'editarVeterinaria', component: EditarVeterinariaComponent},
    {path: 'guardarVeterinaria', component: GuardarVeterinariaComponent}



];
