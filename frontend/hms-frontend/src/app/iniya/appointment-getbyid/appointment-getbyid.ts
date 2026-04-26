import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-id',
  standalone: true,
  imports: [FormsModule, NgIf, JsonPipe],
  templateUrl: './appointment-getbyid.html',
  styleUrl: './appointment-getbyid.css'
})
export class AppointmentGetByIdComponent implements OnInit {

  appointmentId = '';
  data: any = null;
  error = '';

  constructor(private http: HttpClient) {}

  ngOnInit() {
    const savedId = localStorage.getItem('appointmentId');

    if (savedId) {
      this.appointmentId = savedId;
      this.getById();
    }
  }

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:123')
    });
  }

  getById() {
    this.error = '';
    this.data = null;

    if (!this.appointmentId) {
      alert('❌ Please enter Appointment ID');
      return;
    }

    localStorage.setItem('appointmentId', this.appointmentId);

    this.http.get(`http://localhost:9090/api/appointments/${this.appointmentId}`, {
      headers: this.getHeaders()
    }).subscribe({
      next: (res: any) => {
        console.log('FULL RESPONSE:', res);
        this.data = res.data ? res.data : res;
        alert('✅ Appointment fetched successfully');
      },
      error: (err) => {
        console.log('ERROR:', err);
        this.error = err.error?.message || '❌ Appointment not found';
        alert(this.error);
      }
    });
  }

  clear() {
    this.appointmentId = '';
    this.data = null;
    this.error = '';
    localStorage.removeItem('appointmentId');
  }
}