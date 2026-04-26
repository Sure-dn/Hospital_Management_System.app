import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-create-prescription',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './prescriptions-post.html'
})
export class CreatePrescriptionComponent {

  data: any = {};
  response: any;

  constructor(private http: HttpClient) {}

 create() {

  

  const token = localStorage.getItem('token');

const payload = {
  physicianId: this.data.physicianId,
  patientSsn: this.data.patientSsn,
  medicationCode: this.data.medicationCode,
  appointmentId: this.data.appointmentId,
  date: this.data.date,   // ✅ FIXED
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
    next: () => alert("✅ Created"),
    error: (err) => {
      console.error(err);

      if (err.status === 401) {
        alert("🔐 Unauthorized! Login again");
      } else {
        alert("❌ Error: " + err.status);
      }
    }
  });
}
 }
