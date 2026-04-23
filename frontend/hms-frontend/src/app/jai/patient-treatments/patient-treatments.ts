import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor } from '@angular/common';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-patient-treatments',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, CommonModule],
  templateUrl: './patient-treatments.html',
  styleUrl: './patient-treatments.css'
})
export class PatientTreatmentsComponent {

  patientId: any;
  treatments: any[] = [];
  error: string = '';

  constructor(private http: HttpClient) {}

  hasSearched = false;

fetch() {
  this.hasSearched = true;   // 👈 mark that user clicked

  this.error = '';
  this.treatments = [];

  this.http.get(`http://localhost:9090/api/patients/${this.patientId}/treatments`)
    .subscribe({
      next: (res: any) => {
        this.treatments = res.data || res;
      },
      error: () => {
        this.error = "❌ Failed to fetch treatments";
      }
    });
}
}