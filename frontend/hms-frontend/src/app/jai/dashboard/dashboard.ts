import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css'],
  imports: [CommonModule],
})
export class DashboardComponent {

  constructor(private router: Router, private auth: AuthService) {}

  openEndpoint(path: string) {
    console.log("Navigating to:", path);
    this.router.navigate(['/jai', path]);
  }

  logout() {
    this.auth.logout();
  }
}