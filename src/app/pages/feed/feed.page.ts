import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FeedService} from './feed.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.page.html',
  styleUrls: ['./feed.page.scss'],
})


export class FeedPage implements OnInit {
  private events: Array<any>;
  private progress: number;
  constructor(private http: HttpClient,public feedService: FeedService) { }

  ngOnInit() {
    this.feedService.getEvents().subscribe(events => {
      events.time =  new Date().getTime();
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
