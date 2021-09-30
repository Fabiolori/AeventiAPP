import {Component, OnInit} from '@angular/core';
import 'gl-ionic-background-video';

@Component({
  selector: 'succes-welcome',
  templateUrl: './success.page.html',
  styleUrls: ['./success.page.scss'],

})
export class SuccessPage implements OnInit {
  slideOpts = {
    initialSlide: 0,
    speed: 400,
  }

  constructor() {

  }

  ngOnInit() {

  }
}








