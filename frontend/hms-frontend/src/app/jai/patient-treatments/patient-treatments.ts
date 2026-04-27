import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor, DatePipe } from '@angular/common';

@Component({
  selector: 'app-patient-treatments',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, DatePipe],
  templateUrl: './patient-treatments.html',
  styleUrl: './patient-treatments.css'
})
export class PatientTreatmentsComponent {

  patientId: any;
  treatments: any[] = [];
  error: string = '';
  hasSearched = false;

  constructor(private http: HttpClient) {}

  fetch() {
    this.hasSearched = true;
    this.error = '';
    this.treatments = [];

    const token = localStorage.getItem('token'); // 🔥 IMPORTANT

    this.http.get<any>(
      `http://localhost:9090/api/treatments/patient/${this.patientId}`,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({
      next: (res) => {
        console.log("FULL RESPONSE:", res);
        this.treatments = res.data || [];   // ✅ correct mapping
      },
      error: (err) => {
        console.error(err);
        this.error = err.error?.message || "❌ Failed to fetch treatments";
      }
    });
  }
}