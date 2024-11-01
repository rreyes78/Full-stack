import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-buttons',
  templateUrl: './buttons.component.html',
  styleUrls: ['./buttons.component.css']
})
export class ButtonsComponent {
	@Output() loginEvent = new EventEmitter();
	@Output() logoutEvent = new EventEmitter();

  loginbtn: boolean = false;
  logoutbtm: boolean = false;
  


  login(): void {
    this.loginbtn = true;
    this.loginEvent.emit();
  }

  logout(): void {
    this.logoutbtm = true;
    this.loginEvent.emit();
  }

}
