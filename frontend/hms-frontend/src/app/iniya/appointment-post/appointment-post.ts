import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-post',
  standalone: true,
  imports: [FormsModule, NgIf, JsonPipe],
  templateUrl: './appointment-post.html',
  styleUrl: './appointment-post.css'
})
export class AppointmentPostComponent {

  appointmentId = '';
  patientSsn = '';
  prepNurseId = '';
  physicianId = '';
  starttime = '';
  endtime = '';
  examinationRoom = '';

  response: any;
  success = '';
  error = '';

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa('Iniya:123')
    });
  }

  create() {
    this.success = '';
    this.error = '';

    const payload = {
      appointmentId: Number(this.appointmentId),
      patientSsn: Number(this.patientSsn),
      prepNurseId: this.prepNurseId ? Number(this.prepNurseId) : null,
      physicianId: Number(this.physicianId),

      // ✅ Already correct format
      starttime: this.starttime + ':00',
      endtime: this.endtime + ':00',

      examinationRoom: this.examinationRoom
    };

    console.log("Payload 👉", payload);

    this.http.post('http://localhost:9090/api/appointments', payload, {
      headers: this.getHeaders()
    }).subscribe({
      next: (res: any) => {
        this.response = res;
        this.success = '✅ Appointment created successfully';
        alert(this.success);
      },
      error: (err) => {
        console.error(err);
        this.error = err.error?.message || '❌ Failed to create appointment';
        alert(this.error);
      }
    });
  }
}