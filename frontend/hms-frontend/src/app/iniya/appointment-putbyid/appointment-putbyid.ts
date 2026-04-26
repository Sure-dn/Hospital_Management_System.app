import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-put',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './appointment-putbyid.html',
  styleUrl: './appointment-putbyid.css'
})
export class AppointmentPutComponent {

  appointmentId = '';
  patientSsn = '';
  prepNurseId = '';
  physicianId = '';
  starttime = '';
  endtime = '';
  examinationRoom = '';

  success = '';
  error = '';

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa('username:123')
    });
  }

  updateAppointment() {
    this.success = '';
    this.error = '';

    const payload = {
      appointmentId: Number(this.appointmentId),
      patientSsn: Number(this.patientSsn),
      prepNurseId: this.prepNurseId ? Number(this.prepNurseId) : null,
      physicianId: Number(this.physicianId),

      // important fix
      starttime: this.starttime + ':00',
      endtime: this.endtime + ':00',

      examinationRoom: this.examinationRoom
    };

    console.log('UPDATE PAYLOAD:', payload);

    this.http.put(`http://localhost:9090/api/appointments/${this.appointmentId}`, payload, {
      headers: this.getHeaders()
    }).subscribe({
      next: () => {
        this.success = '✅ Appointment updated successfully';
        alert(this.success);
      },
      error: (err) => {
        console.error('FULL ERROR:', err);
        this.error = err.error?.message || err.error || '❌ Failed to update appointment';
        alert(this.error);
      }
    });
  }
}