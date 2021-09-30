import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {NewEventService} from './new-event.service';
import {TokenManagerService} from '../../TokenManagerService';
import {Router} from "@angular/router";
import {ToastController} from "@ionic/angular";

@Component({
  selector: 'app-new-event',
  templateUrl: './new-event.page.html',
  styleUrls: ['./new-event.page.scss'],
  providers: [NewEventService]
})
export class NewEventPage implements OnInit {
  newEventForm: FormGroup;

  constructor(private newEventService: NewEventService, private tokenStorage: TokenManagerService, private router: Router, private toastController: ToastController) {
    this.newEventForm = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      time: new FormControl(),
      type: new FormControl()
    });
  }

  async loginMessage() {
    const toast = await this.toastController.create({
      message: 'Per usare Aeventi effettua il login!',
      duration: 5000,
      animated: true,
      color: 'danger',
      position: 'middle'
    });
    await toast.present();
  }

  ngOnInit() {
    if (this.tokenStorage.getToken() == null || this.tokenStorage.getToken() === 'auth-token') {
      this.router.navigate(['/login']).then(r => {
      });
      this.loginMessage();
      return;
    }
  }


  create() {
    this.newEventService.addEvent(this.newEventForm.value).subscribe(() => {
        alert('Evento aggiunto correttamente!');
        console.log(this.newEventForm.value);
      }
      , error => {
        console.log(error);
      }
    );
  }
}
