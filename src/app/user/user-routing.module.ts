import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ListeUserComponent } from './liste-user/liste-user.component';
import { AddUserComponent } from './add-user/add-user.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { HomeUserComponent } from './home-user/home-user.component';

const routes: Routes = [
  { path: '', component: HomeUserComponent , children: [ 
  { path: 'listeUser', component: ListeUserComponent },
  { path: 'addUser', component: AddUserComponent},
  { path: 'updateUser/:idUser', component: UpdateUserComponent },

]
},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
