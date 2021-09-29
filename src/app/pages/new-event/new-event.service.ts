import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {TokenManagerService} from '../../TokenManagerService';
import {api} from '../../../environments/environment';

@Injectable()
export class NewEventService {


  constructor(public http: HttpClient, private tokenStorage: TokenManagerService) {
  }

  addEvent(event): Observable<Event> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json;charset=UTF-8')
      .set('Authorization', this.tokenStorage.getToken());
    const options = { headers };
    const response = this.http.post<Event>(api + 'events',event, options);
    console.log(event);
    return response;
  }

  catch(error) {
    console.error(error.status);
    console.error(error.error);
    console.error(error.headers);
  }



}
