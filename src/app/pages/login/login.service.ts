import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable()
export class LoginService {
  public loggedAccount: any;


  constructor(public http: HttpClient) {
  }


  login(email, password) {
    const headers = new HttpHeaders()
      .set('Content-Type', 'application/json;charset=UTF-8');

    const options = { headers : headers};
    return this.http.post<Account>('http://localhost:8080/accounts/auth', { values : email, password },options)
      .pipe(map(account => {
        localStorage.setItem('account', JSON.stringify(account));
        this.loggedAccount.next(account);
        return account;
      }));
  }

  catch(error) {
    console.error(error.status);
    console.error(error.error);
    console.error(error.headers);
  }



}
