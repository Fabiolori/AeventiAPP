import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {IonicModule} from '@ionic/angular';

import {RegisterPageRoutingModule} from './register-routing.module';

import {RegisterPage} from './register.page';

import {RegisterService} from './register.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RegisterPageRoutingModule,
    ReactiveFormsModule
  ],
  providers: [RegisterService],
  declarations: [RegisterPage]
})
export class RegisterPageModule {}
