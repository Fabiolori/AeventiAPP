import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {api} from '../../../environments/environment';

@Injectable()
export class RegisterService {


  constructor(public http: HttpClient) {
  }

  addAccount(account): Observable<Account> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json;charset=UTF-8');

    const options = {headers: headers};
    const response = this.http.post<Account>(api + 'public/accounts', account.value, options);
    console.log('Registro: ' + account.value.name);
    return response;
  }

  catch(error) {
    console.error(error.status);
    console.error(error.error);
    console.error(error.headers);
  }


}
