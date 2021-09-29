import {Component, OnInit} from '@angular/core';
import {TokenManagerService} from '../../TokenManagerService';
import {Router} from '@angular/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
})
export class SettingsPage implements OnInit {

  constructor(private tokenStorage: TokenManagerService,private router: Router) { }

  ngOnInit() {
    console.log(this.tokenStorage.isLogged());
    if (this.tokenStorage.isLogged() === 'false'){
      this.router.navigate(['/login']).then(r => {});
    }
  }
  logout(){
    this.tokenStorage.signOut();
    this.router.navigate(['/login']).then(r => {});
    window.location.reload();
  }
}
