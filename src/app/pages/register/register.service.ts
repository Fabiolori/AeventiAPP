import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class RegisterService {


  constructor(public http: HttpClient) {
  }

  addAccount(account): Observable<Account> {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json;charset=UTF-8');

    const options = { headers : headers };
    const response = this.http.post<Account>('http://localhost:8080/accounts',account.value, options);
    return response;
  }

  catch(error) {
    console.error(error.status);
    console.error(error.error);
    console.error(error.headers);
  }



}
