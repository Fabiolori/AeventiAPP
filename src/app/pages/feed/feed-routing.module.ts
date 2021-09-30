import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {FeedPage} from './feed.page';

const routes: Routes = [
  {
    path: '',
    component: FeedPage
  },
  {
    path: 'new-event',
    loadChildren: () => import('src/app/pages/new-event/new-event.module').then(m => m.NewEventPageModule)
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
export class FeedPageRoutingModule {
}
