import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {NewEventService} from './new-event.service';

@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.page.html',
  styleUrls: ['./new-event.page.scss'],
  providers: [NewEventService]
})
export class NewEventPage implements OnInit {
  newEventForm: FormGroup;

  constructor(private newEventService: NewEventService) {
    this.newEventForm = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      time: new FormControl(),
      type: new FormControl()
    });

  }

  ngOnInit() {
  }


  create() {
    this.newEventService.addEvent(this.newEventForm.value).subscribe(() =>{
        alert('Evento aggiunto correttamente!');
        console.log(this.newEventForm.value);
      }
      , error => {
        console.log(error);}
    );




  }
}
