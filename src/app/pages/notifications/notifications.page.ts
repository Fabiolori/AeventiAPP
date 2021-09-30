import {Component, OnInit} from '@angular/core';
import {ToastController} from '@ionic/angular';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.page.html',
  styleUrls: ['./notifications.page.scss'],
})
export class NotificationsPage implements OnInit {

  constructor(private toastController: ToastController) {
  }

  async presentToast() {
    const toast = await this.toastController.create({
      message: 'Le notifiche non sono al momento disponibili',
      duration: 5000,
      animated: true,
      color: 'warning',
      position: 'middle'
    });
    await toast.present();
  }

  ngOnInit() {
    this.presentToast();
  }

}
