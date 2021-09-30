import {Component} from '@angular/core';
import {TokenManagerService} from './TokenManagerService';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  private page3;
  private page3end;

  constructor(private tokenStorage: TokenManagerService) {
    if (this.tokenStorage.isLogged() === 'false') {
      this.page3 = 'LogIn';
      this.page3end = 'login';
    } else {
      this.page3 = 'Account';
      this.page3end = 'settings';
    }
  }

}
