import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FeedService} from './feed.service';
import {ToastController} from "@ionic/angular";
import {TokenManagerService} from "../../TokenManagerService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-feed',
  templateUrl: './feed.page.html',
  styleUrls: ['./feed.page.scss'],
})


export class FeedPage implements OnInit {
  private events: Array<any>;
  private progress: number;

  constructor(private http: HttpClient, public feedService: FeedService, private toastController: ToastController, private tokenStorage: TokenManagerService, private router: Router) {
  }

  async emptyEventsMessage() {
    const toast = await this.toastController.create({
      message: 'Nessun evento trovato!',
      duration: 5000,
      animated: true,
      color: 'danger',
      position: 'middle'
    });
    await toast.present();
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
    this.feedService.getEvents().subscribe(events => {
      if (events == null) {
        return this.emptyEventsMessage();
      }
      events.time = new Date().getTime();
      this.events = events;

    });
  }

  setPercentBar(i) {
    setTimeout(() => {
      const apc = (i / 100);
      this.progress = apc;
    }, 20 * i);

  }

  refresh(event) {
    console.log('Updating Events');
    this.ngOnInit();
    for (let index = 0; index <= 100; index++) {
      this.setPercentBar(+index);
    }
    setTimeout(() => {
      console.log('Finished');
      event.target.complete();
    }, 2000);
  }
}
