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

  create() {

    this.errors = {};
    this.backendError = '';
    this.success = '';
    this.response = null;
    this.cd.detectChanges();

    const payload = {
      appointmentId: Number(this.appointment.appointmentId),
      patientSsn: Number(this.appointment.patientSsn),
      prepNurseId: this.appointment.prepNurseId
        ? Number(this.appointment.prepNurseId)
        : null,
      physicianId: Number(this.appointment.physicianId),

      // ✅ Backend format
      starttime: this.appointment.starttime
        ? this.appointment.starttime + ':00'
        : null,

      endtime: this.appointment.endtime
        ? this.appointment.endtime + ':00'
        : null,

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

        // ✅ Reset form
        this.appointment = {
          appointmentId: '',
          patientSsn: '',
          prepNurseId: '',
          physicianId: '',
          starttime: '',
          endtime: '',
          examinationRoom: ''
        };

        this.cd.detectChanges();

        setTimeout(() => {
          alert('✅ Appointment created successfully');
        }, 0);
      },

      error: (err) => {
        console.error("FULL ERROR:", err);

        if (typeof err.error === 'string') {
          this.backendError = err.error;
        }
        else if (err.error?.message) {
          this.backendError = err.error.message;
        }
        else if (err.error?.fieldErrors) {
          this.errors = { fieldErrors: err.error.fieldErrors };
        }
        else if (err.error) {
          this.errors = { ...err.error };
        }
        else {
          this.backendError = 'Error while creating appointment';
        }

        this.cd.detectChanges();

        setTimeout(() => {
          alert(this.backendError || 'Validation error occurred');
        }, 0);
      }
    });
  }
}