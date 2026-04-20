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
    { name: 'Sureka', role: 'Patient & Appointment' },
    { name: 'Jai', role: 'Physician & Department' },
    { name: 'Iniya', role: 'Nursing & Rooms' },
    { name: 'Ashmitha', role: 'Medication & Prescription' },
    { name: 'John', role: 'Stay, Procedures & Treatments' }
  ];

  constructor(private router: Router) {}

  goToLogin(member: any) {
    this.router.navigate(['/login'], {
      queryParams: { user: member.name }
    });
  }
}