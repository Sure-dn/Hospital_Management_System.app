import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service'; // adjust path if needed
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-loginpage',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './loginpage.html',
  styleUrl: './loginpage.css',
})
export class Loginpage {

  username: string = '';
  password: string = '';
  errorMsg: string = '';
  redirectRoute: string = ''; // 🔥 important

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private auth: AuthService
  ) {

    // get data from home page
    this.route.queryParams.subscribe(params => {
      this.username = params['user'] || '';
      this.redirectRoute = params['route'] || '';
    });
  }

  goback() {
    this.router.navigate(['/']);
  }

  onLogin() {

    const data = {
      username: this.username,
      password: this.password
    };

    this.auth.login(data).subscribe({

      next: (res) => {
        // store session
        this.auth.setSession(res.role);

        // redirect to correct page
        this.router.navigate([`/${this.redirectRoute}`]);
      },

      error: () => {
        this.errorMsg = 'Invalid username or password ❌';
      }
    });
  }
}