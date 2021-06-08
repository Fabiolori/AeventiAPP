import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class FeedService {


  constructor(public http: HttpClient) {
  }

  getEvents(): Observable<any> {
    return this.http.get('http://localhost:8080/events');
  }
}
