import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {RegisterPage} from './register.page';

const routes: Routes = [
  {
    path: '',
    component: RegisterPage
  },
  {
    path: 'feed',
    loadChildren: () => import('src/app/pages/feed/feed.module').then(m => m.FeedPageModule)
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
export class RegisterPageRoutingModule {
}
