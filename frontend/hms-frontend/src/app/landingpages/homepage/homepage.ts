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
    { name: 'Sureka', role: 'Nursing & Rooms' },
    { name: 'Jai', role: 'Procedures & Treatments' },
    { name: 'Iniya', role: 'Patient & Appointment' },
    { name: 'Ashmitha', role: 'Physician & Department' },
    { name: 'John', role: 'Medication & Prescription  ' }
  ];

  constructor(private router: Router) {}

  goToLogin(member: any) {
    this.router.navigate(['/login'], {
      queryParams: { user: member.name }
    });
  }
}