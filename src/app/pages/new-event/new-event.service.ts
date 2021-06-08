import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class NewEventService {


  constructor(public http: HttpClient) {
  }

  addEvent(event): Observable<Event> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json;charset=UTF-8');

    const options = { headers: headers };

    const response = this.http.post<Event>('http://localhost:8080/events',event, options);
    console.log(event);
    return response;
  }

  catch(error) {
    console.error(error.status);
    console.error(error.error);
    console.error(error.headers);
  }



}
