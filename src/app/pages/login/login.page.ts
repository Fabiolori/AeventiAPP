import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';


import {LoginService} from './login.service';
import {ok} from 'assert';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
  providers: [LoginService]

})
export class LoginPage implements OnInit {
  loginForm: FormGroup;

  constructor(public loginService: LoginService, private router: Router) {
    this.loginForm = new FormGroup({
      email: new FormControl(),
      password: new FormControl()
    });
  }

  ngOnInit() {
  }


  login() {
    this.loginService.login(this.loginForm.value.email, this.loginForm.value.password).subscribe(account => {
        if (account.name !== this.loginForm.value.email) {
          return ('Credenziali Errate');
        }
        alert('Bentornato!');
        console.log(this.loginForm.value);
        return ok({
          id: account.id,
          name: account.name

        });


      }
      , error => {
        console.log(error);
      }
    );

  }

}


