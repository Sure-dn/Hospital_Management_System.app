import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor,DatePipe } from '@angular/common';

@Component({
  selector: 'app-patient-stays',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, DatePipe],
  templateUrl: './patient-stays.html',
  styleUrl: './patient-stays.css'
})
export class PatientStaysComponent {

  ssn: any;
  stays: any[] = [];
  error = '';

  constructor(private http: HttpClient) {}

  loading = false;


load() {
  this.error = '';
  this.stays = [];
  this.loading = true;

  const token = localStorage.getItem('token');

  this.http.get<any>(
    `http://localhost:9090/api/stays/patient/${this.ssn}`,
    {
      headers: {
        Authorization: 'Bearer ' + token
      }
    }
  ).subscribe({
    next: (res) => {
      console.log("FULL RESPONSE:", res);

      // 🔥 IMPORTANT
      this.stays = res?.data || [];

      this.loading = false;   // ✅ MUST BE HERE
    },
    error: (err) => {
      console.error(err);
      this.error = err.error?.message || 'Error ❌';

      alert(err.error?.message); // 🔥 popup

      this.loading = false;   // ✅ ALSO HERE
    },
    complete: () => {
      this.loading = false;   // ✅ EXTRA SAFETY
    }
  });
}
}