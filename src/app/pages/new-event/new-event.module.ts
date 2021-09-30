import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {IonicModule} from '@ionic/angular';

import {NewEventPageRoutingModule} from './new-event-routing.module';

import {NewEventPage} from './new-event.page';
import {NewEventService} from './new-event.service';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NewEventPageRoutingModule,
    ReactiveFormsModule
  ],
  providers: [NewEventService],
  declarations: [NewEventPage]
})
export class NewEventPageModule {
}
