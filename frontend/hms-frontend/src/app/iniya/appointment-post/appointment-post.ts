import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-appointment-post',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './appointment-post.html',
  styleUrl: './appointment-post.css'
})
export class AppointmentPostComponent {

  appointment = {
    appointmentId: '',
    patientSsn: '',
    prepNurseId: '',
    physicianId: '',
    starttime: '',
    endtime: '',
    examinationRoom: ''
  };

  errors: any = {};
  backendError = '';
  success = '';
  response: any;

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  getHeaders() {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa('Iniya:123')
    });
  }

  // ✅ Convert UI format → Backend format
  formatDateTime(value: string) {
    if (!value) return null;

    // Input: 2026-04-29T14:30 (from datetime-local)
    return value + ':00';
  }

  create() {

    this.errors = {};
    this.backendError = '';
    this.success = '';

    const payload = {
      appointmentId: Number(this.appointment.appointmentId),
      patientSsn: Number(this.appointment.patientSsn),
      prepNurseId: this.appointment.prepNurseId
        ? Number(this.appointment.prepNurseId)
        : null,
      physicianId: Number(this.appointment.physicianId),

      starttime: this.formatDateTime(this.appointment.starttime),
      endtime: this.formatDateTime(this.appointment.endtime),

      examinationRoom: this.appointment.examinationRoom
    };

    console.log("Payload 👉", payload);

    this.http.post(
      'http://localhost:9090/api/appointments',
      payload,
      { headers: this.getHeaders() }
    ).subscribe({

      next: (res: any) => {
        this.response = res;
        this.success = 'Appointment Created Successfully';

        // Reset form
        this.appointment = {
          appointmentId: '',
          patientSsn: '',
          prepNurseId: '',
          physicianId: '',
          starttime: '',
          endtime: '',
          examinationRoom: ''
        };

        alert('✅ Appointment created successfully');
        this.cd.detectChanges();
      },

      error: (err) => {
        console.error("ERROR:", err);

        this.backendError =
          err.error?.message ||
          err.error ||
          'Something went wrong';

        alert(this.backendError);
        this.cd.detectChanges();
      }
    });
  }
}