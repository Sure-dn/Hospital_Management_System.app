import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
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
  redirectRoute: string = '';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private auth: AuthService
  ) {
    this.route.queryParams.subscribe(params => {

      // ✅ set username only from guard
      this.username = params['user'] || '';

      // ✅ set redirect route
      this.redirectRoute = params['route'] || '';

      // 🔥 IMPORTANT: always clear password
      this.password = '';

      // clear old errors
      this.errorMsg = '';
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

      next: (token: string) => {
        this.auth.setSession(token);

        // fallback if no route
        const route = this.redirectRoute || 'jai';

        this.router.navigate([`/${route}`]);
      },

      error: () => {
        this.errorMsg = 'Invalid username or password ❌';

        // 🔥 clear password after wrong attempt
        this.password = '';
      }
    });
  }
}