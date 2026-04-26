import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';


@Component({
  selector: 'app-appointment-delete',
  standalone: true,
  imports: [FormsModule,NgIf],
  templateUrl: './appointment-deletebyid.html',
  styleUrl: './appointment-deletebyid.css'
})
export class AppointmentDeleteComponent {

  appointmentId = '';
  success = '';
  error = '';

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('username:123')
    });
  }

  deleteAppointment() {
    this.success = '';
    this.error = '';

    this.http.delete(`http://localhost:9090/api/appointments/${this.appointmentId}`, {
      headers: this.getHeaders()
    }).subscribe({
      next: () => {
        this.success = '✅ Appointment deleted successfully';
        alert(this.success);
      },
      error: (err) => {
        console.error(err);
        this.error = err.status === 401
          ? '❌ Unauthorized'
          : err.error?.message || '❌ Failed to delete appointment';
        alert(this.error);
      }
    });
  }
}