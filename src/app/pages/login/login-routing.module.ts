import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {LoginPage} from './login.page';

const routes: Routes = [
  {
    path: '',
    component: LoginPage
  },
  {
    path: 'success',
    loadChildren: () => import('src/app/pages/success/success.module').then(m => m.SuccessPageModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class LoginPageRoutingModule {
}
