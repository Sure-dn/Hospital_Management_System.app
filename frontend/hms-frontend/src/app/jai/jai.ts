import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-jai',
  standalone: true,
  templateUrl: './jai.html',
  styleUrls: ['./jai.css'],
})
export class JaiComponent {

  constructor(private router: Router, private auth: AuthService) {}

 openEndpoint(path: string) {
  console.log("Navigating to:", path);
  this.router.navigate([path]);
 }
 logout() {
    this.auth.logout();
  }
}