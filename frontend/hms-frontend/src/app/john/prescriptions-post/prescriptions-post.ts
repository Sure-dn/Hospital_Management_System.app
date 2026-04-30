import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-create-prescription',
  standalone: true,
  imports: [FormsModule, JsonPipe, NgIf],
  templateUrl: './prescriptions-post.html'
})
export class CreatePrescriptionComponent {

  data: any = {};
  response: any;
  errorMsg: string = '';

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

 create() {
  this.errorMsg = '';

  const token = localStorage.getItem('token');

  const payload = {
    physicianId: this.data.physicianId,
    patientSsn: this.data.patientSsn,
    medicationCode: this.data.medicationCode,
    appointmentId: this.data.appointmentId,
    date: this.data.date,
    dose: this.data.dose
  };

  this.http.post(
    'http://localhost:9090/api/prescriptions',
    payload,
    {
      headers: {
        Authorization: 'Bearer ' + token
      }
    }
  ).subscribe({
    next: (res) => {
      this.response = res;
      this.cdr.detectChanges();
      alert("✅ Created");
    },
    error: (err) => {
      this.errorMsg = (typeof err.error === 'string' && err.error.trim()) ? err.error.trim() : `Error \${err.status}: \${err.statusText || err.message}`;
      this.cdr.detectChanges();
      if (err.status === 401) {
        alert("🔐 Unauthorized! Login again");
      } else {
        alert("❌ Error: " + err.status);
      }
    }
  });
 }
}
