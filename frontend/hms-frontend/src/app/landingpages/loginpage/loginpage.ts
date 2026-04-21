import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-loginpage',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './loginpage.html',
  styleUrl: './loginpage.css',
})
export class Loginpage {
  username: string = '';
  password: string = '';
  errorMsg: string = '';

  constructor(private router: Router) {}

  goback() {
    this.router.navigate(['/home']);
  }
  onLogin() {
    // Placeholder for actual authentication logic
    if (this.username === 'admin' && this.password === 'password') {
      alert('Login successful!');
      this.errorMsg = '';
    } else {
      this.errorMsg = 'Invalid username or password';
    }
  }
}
