import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  message = 'Hello, World!';

  changeMessage(): void {
    this.message = 'Hi there and greetings!';
  }
}
