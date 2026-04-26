import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-physician',
  standalone: true,
  imports: [FormsModule,NgFor,NgIf],
  templateUrl: './appointment-getbyphysicianid.html',
  styleUrl: './appointment-getbyphysicianid.css'
})
export class AppointmentGetByPhysicianComponent {

  employeeId = '';
  appointments: any[] = [];
  error = '';

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('username:123')
    });
  }

  getByPhysician() {
    this.error = '';
    this.appointments = [];

    this.http.get<any[]>(`http://localhost:9090/api/physicians/${this.employeeId}/appointments`, {
      headers: this.getHeaders()
    }).subscribe({
      next: (res) => {
        this.appointments = res;
        alert('✅ Physician appointments loaded');
      },
      error: (err) => {
        console.error(err);
        this.error = err.status === 401
          ? '❌ Unauthorized'
          : err.error?.message || '❌ Failed to load physician appointments';
        alert(this.error);
      }
    });
  }
}