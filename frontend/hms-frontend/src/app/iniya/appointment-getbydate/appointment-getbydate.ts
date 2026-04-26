import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-date',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './appointment-getbydate.html',
  styleUrl: './appointment-getbydate.css'
})
export class AppointmentGetByDateComponent {

  date = '';
  appointments: any[] = [];
  error = '';
  searched = false;

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('username:123')
    });
  }

  getByDate() {
    this.error = '';
    this.appointments = [];
    this.searched = true;

    if (!this.date) {
      alert('❌ Please select a date');
      return;
    }

    this.http.get<any>(`http://localhost:9090/api/appointments/by-date?date=${this.date}`, {
      headers: this.getHeaders()
    }).subscribe({
      next: (res: any) => {
        console.log("FULL RESPONSE:", res);

        // 🔥 IMPORTANT FIX
        this.appointments = res.data ? res.data : res;

        alert('✅ Appointments loaded by date');
      },
      error: (err) => {
        console.error(err);
        this.error = err.error?.message || '❌ Failed to load appointments';
        alert(this.error);
      }
    });
  }
}