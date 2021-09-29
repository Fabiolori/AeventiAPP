import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';


import {LoginService} from './login.service';
import {ok} from 'assert';
import {TokenManagerService} from '../../TokenManagerService';
import {ToastController} from "@ionic/angular";

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
  providers: [LoginService]

})
export class LoginPage implements OnInit {
  loginForm: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = 'Credenziali Errate';
  roles: string[] = [];

  constructor(public loginService: LoginService, private router: Router, private tokenStorage: TokenManagerService,private toastController: ToastController) {
    this.loginForm = new FormGroup({
      username: new FormControl(),
      password: new FormControl()
    });
  }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  async failedMessage() {
    const toast = await this.toastController.create({
      message: 'Credenziali errate o account non ancora registrato!',
      duration: 5000,
      animated: true,
      color: 'warning',
      position: 'middle'
    });
    await toast.present();
  }

  login() {
    this.loginService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(
      data => {
        //TODO SPRIGBOOT NON AUTORIZZA ANCHE SE CI METTO IL TOKEN
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.router.navigate(['/success']);
        console.log('login effettuato');

      },
        err => {
        console.log('credenziali errate');
          this.errorMessage = err.error.message;
          this.isLoginFailed = true;
          this.failedMessage();
        }
    );

  }


}


