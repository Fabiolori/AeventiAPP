import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {RegisterService} from '../register/register.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
  providers: [RegisterService]
})

export class RegisterPage implements OnInit {
  registerForm: FormGroup;


  constructor(public registerService: RegisterService, private router: Router) {
    this.registerForm = new FormGroup({
      email: new FormControl(),
      password: new FormControl(),
      name: new FormControl(),
      surname: new FormControl()
    });
  }

  ngOnInit() {
  }

  register() {

 this.registerService.addAccount(this.registerForm).subscribe(() =>{
   alert('Benvenuto ' + this.registerForm.value.name + '!');
     this.router.navigate(['/success']);
 }
   , error => {
     console.log(error);}
 );




  }
}
