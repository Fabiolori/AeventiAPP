import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {NewEventPage} from './new-event.page';

const routes: Routes = [
  {
    path: '',
    component: NewEventPage
  },
  {
    path: 'login',
    loadChildren: () => import('src/app/pages/login/login.module').then(m => m.LoginPageModule)
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NewEventPageRoutingModule {
}
