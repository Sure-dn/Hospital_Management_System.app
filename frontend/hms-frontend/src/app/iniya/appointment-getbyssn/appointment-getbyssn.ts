import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-patient',
  standalone: true,
  imports: [FormsModule,NgFor, NgIf],
  templateUrl: './appointment-getbyssn.html',
  styleUrl: './appointment-getbyssn.css'
})
export class AppointmentGetByPatientComponent {

  ssn = '';
  appointments: any[] = [];
  error = '';

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('username:123')
    });
  }

  getByPatient() {
    this.error = '';
    this.appointments = [];

    this.http.get<any[]>(`http://localhost:9090/api/patients/${this.ssn}/appointments`, {
      headers: this.getHeaders()
    }).subscribe({
      next: (res) => {
        this.appointments = res;
        alert('✅ Patient appointments loaded');
      },
      error: (err) => {
        console.error(err);
        this.error = err.status === 401
          ? '❌ Unauthorized'
          : err.error?.message || '❌ Failed to load patient appointments';
        alert(this.error);
      }
    });
  }
}