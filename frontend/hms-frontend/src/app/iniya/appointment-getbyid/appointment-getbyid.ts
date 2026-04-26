import { Component } from '@angular/core';
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
export class AppointmentGetByIdComponent {

  appointmentId = '';
  data: any;
  error = '';

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('admin:123')
    });
  }

  getById() {
    this.error = '';
    this.data = null;

    this.http.get(`http://localhost:9090/api/appointments/${this.appointmentId}`, {
      headers: this.getHeaders()
    }).subscribe({
      next: (res) => {
        this.data = res;
      },
      error: (err) => {
        this.error = err.error?.message || '❌ Appointment not found';
        alert(this.error);
      }
    });
  }
}