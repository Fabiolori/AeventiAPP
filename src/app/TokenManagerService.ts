import {Injectable} from '@angular/core';
import { Cookie } from 'ng2-cookies/ng2-cookies';
//VERRANNO SOVRASCRITTI AL LOGIN
const TOKEN_KEY = 'auth-token';
const REFRESHTOKEN_KEY = 'auth-refreshtoken';
const USER_KEY = 'auth-user';
@Injectable({
  providedIn: 'root'
})
export class TokenManagerService {
  constructor() {
  }

  signOut(): void {
    Cookie.set('logged', 'false');
    sessionStorage.clear();
    Cookie.delete('id_token');
  }

  public saveToken(token: string): void {
    Cookie.set('logged', 'true');
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.setItem(TOKEN_KEY, token);
    Cookie.set('id_token', token, 0.0138889);
  }

  public getToken(): string | null {
    //return sessionStorage.getItem(TOKEN_KEY);
    return Cookie.get('id_token');
  }

  public saveRefreshToken(token: string): void {
    sessionStorage.removeItem(REFRESHTOKEN_KEY);
    sessionStorage.setItem(REFRESHTOKEN_KEY, token);
    Cookie.set('id_token', token, 0.0138889);
  }

  public getRefreshToken(): string | null {
    return Cookie.get('id_token');
  }
  public saveUser(user: any): void {
    sessionStorage.removeItem(USER_KEY);
    sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }
  public isLogged(): string {
   return Cookie.get('logged');

  }
}
