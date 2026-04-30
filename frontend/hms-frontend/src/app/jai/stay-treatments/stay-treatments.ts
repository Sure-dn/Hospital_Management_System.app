import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-stay-treatments',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './stay-treatments.html',
  styleUrl: './stay-treatments.css'
})
export class StayTreatmentsComponent {

  stayId: any;
  treatments: any[] = [];
  error = '';
  loading = false;
  hasSearched = false;

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  load() {

    // 🔴 1. EMPTY INPUT VALIDATION
    if (!this.stayId) {
      alert("Stay ID should not be empty");
      return;
    }

    this.error = '';
    this.treatments = [];
    this.loading = true;
    this.hasSearched = true;

    const token = localStorage.getItem('token');

    this.http.get<any>(
      `http://localhost:9090/api/treatments/stay/${this.stayId}`,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({

      next: (res) => {
  console.log("RESPONSE:", res);

  // 🔥 FORCE CORRECT EXTRACTION
  this.treatments = res?.data ? [...res.data] : [];

  console.log("FINAL:", this.treatments);

  this.loading = false;

  // ❌ DO NOT set error
  if (this.treatments.length === 0) {
    alert(`No treatments found for stay ID ${this.stayId}`);
  }

  this.cd.detectChanges(); // 🔥 REQUIRED
},

      error: (err) => {
        console.error(err);

        let message = "Something went wrong";

        if (typeof err.error === 'string') {
          message = err.error;
        } else if (err.error?.message) {
          message = err.error.message;
        } else if (err.message) {
          message = err.message;
        }

        // 🔴 3. BACKEND ERROR ALERT
        alert(message);

        this.error = message;
        this.loading = false;
        this.treatments = [];

        this.cd.detectChanges();
      }
    });
  }

  // 🔄 CLEAR ON INPUT CHANGE
  onIdChange() {
    if (this.hasSearched) {
      this.treatments = [];
      this.error = '';
      this.loading = false;
      this.hasSearched = false;

      this.cd.detectChanges();
    }
  }
}