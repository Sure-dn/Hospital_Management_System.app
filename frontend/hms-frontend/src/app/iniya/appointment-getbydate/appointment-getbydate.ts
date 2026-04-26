import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-date',
  standalone: true,
  imports: [FormsModule, NgFor,NgIf],
  templateUrl: './appointment-getbydate.html',
  styleUrl: './appointment-getbydate.css'
})
export class AppointmentGetByDateComponent {

  date = '';
  appointments: any[] = [];
  error = '';

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('username:123')
    });
  }

  getByDate() {
    this.error = '';
    this.appointments = [];

    this.http.get<any[]>(`http://localhost:9090/api/appointments/by-date?date=${this.date}`, {
      headers: this.getHeaders()
    }).subscribe({
      next: (res) => {
        this.appointments = res;
        alert('✅ Appointments loaded by date');
      },
      error: (err) => {
        console.error(err);
        this.error = err.status === 401
          ? '❌ Unauthorized'
          : err.error?.message || '❌ Failed to load appointments by date';
        alert(this.error);
      }
    });
  }
}