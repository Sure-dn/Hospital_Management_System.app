import { Component, ChangeDetectorRef } from '@angular/core';
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
  loading = false;

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  fetch() {
    this.hasSearched = true;
    this.error = '';
    this.loading = true;
    this.treatments = []; // 🔥 reset

    const token = localStorage.getItem('token');

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

        // 🔥 handle both wrapped & direct
        this.treatments = Array.isArray(res) ? res : (res.data || []);

        this.loading = false;

        if (this.treatments.length === 0) {
          this.error = 'No treatments found';
        } else {
          this.error = '';
        }

        this.cd.detectChanges(); // 🔥 ensure UI update
      },
      error: (err) => {
        console.error(err);

        let message = "Something went wrong";

        if (typeof err.error === 'string') {
          message = err.error;
        } else if (err.error?.message) {
          message = err.error.message;
        }

        alert(err.error.message); // 🔥 popup

        this.error = message;
        this.loading = false;
        this.treatments = [];

        this.cd.detectChanges();
      }
    });
  }

  // 🔥 CLEAR ON INPUT CHANGE
  onIdChange() {
    if (this.hasSearched) {
      this.treatments = [];
      this.error = '';
      this.hasSearched = false;
      this.loading = false;

      this.cd.detectChanges();
    }
  }
}