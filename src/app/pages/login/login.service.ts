import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {api} from "../../../environments/environment";
import {TokenManagerService} from "../../TokenManagerService";

@Injectable()
export class LoginService {
  public loggedAccount: any;


  constructor(public http: HttpClient, private tokenStorage: TokenManagerService) {
  }


  login(username: string, password: string): Observable<any> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json;charset=UTF-8');
    const options = {headers: headers};
    return this.http.post(api + 'public/login', {
      username,
      password
    }, options);
  }

  catch(error) {
    console.error(error.status);
    console.error(error.error);
    console.error(error.headers);
  }


  refreshToken(token: string) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json;charset=UTF-8')
      .set('Authorization', this.tokenStorage.getToken());
    const options = {headers};
    return this.http.get(api + 'refresh-token', options);
  }
}
