import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {api} from '../../../environments/environment';
import {TokenManagerService} from '../../TokenManagerService';

@Injectable()
export class FeedService {


  constructor(public http: HttpClient, private tokenStorage: TokenManagerService) {
  }
  getEvents(): Observable<any> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json;charset=UTF-8')
      .set('Authorization', this.tokenStorage.getToken());
    const options = { headers };
    return this.http.get(api +'events',options);
  }
}
