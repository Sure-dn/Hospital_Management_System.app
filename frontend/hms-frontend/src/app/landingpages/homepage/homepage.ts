import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './homepage.html',
  styleUrls: ['./homepage.css']
})
export class HomeComponent {

  members = [
  { name: 'Sureka', role: 'Nursing & Rooms', route: 'sureka' },
  { name: 'Jai', role: 'Procedures & Treatments', route: 'jai' },
  { name: 'Iniya', role: 'Patient & Appointment', route: 'iniya' },
  { name: 'Ashmitha', role: 'Physician & Department', route: 'ashmitha' },
  { name: 'John', role: 'Medication & Prescription', route: 'john' }
];

  constructor(private router: Router) {}

  goToLogin(member: any) {
  this.router.navigate(['/login'], {
    queryParams: {
      user: member.name,
      route: member.route   // 🔥 THIS IS THE KEY
    }
  });
}
}