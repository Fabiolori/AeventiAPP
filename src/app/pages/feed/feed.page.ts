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
  constructor(private http: HttpClient,public feedService: FeedService) { }

  ngOnInit() {
    this.feedService.getEvents().subscribe(events => {
      events.time =  new Date().getTime();
      this.events = events;

    });
  }


  refresh(event) {
    console.log('Updating Events');
    this.ngOnInit();
    setTimeout(() => {
      console.log('Finished');
      event.target.complete();
    }, 2000);
}
}
