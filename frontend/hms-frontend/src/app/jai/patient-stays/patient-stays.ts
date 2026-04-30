import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor, DatePipe } from '@angular/common';

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
  loading = false;
  hasSearched = false;

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  load() {

    // 🔴 EMPTY INPUT VALIDATION
    if (!this.ssn) {
      alert("Patient ID should not be empty");
      return;
    }

    this.error = '';
    this.stays = [];
    this.loading = true;
    this.hasSearched = true;

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

        this.stays = res?.data || [];

        this.loading = false;

        // 🔴 NO DATA ALERT
        if (this.stays.length === 0) {
          alert(`No stays found for patient ID ${this.ssn}`);
        }

        this.cd.detectChanges();
      },

      error: (err) => {
        console.error("ERROR:", err);

        let msg = "Something went wrong";

        if (err.status === 401) {
          msg = "Unauthorized! Please login again";
        } 
        else if (typeof err.error === 'string') {
          msg = err.error;
        } 
        else if (err.error?.message) {
          msg = err.error.message;
        } 
        else if (err.message) {
          msg = err.message;
        }

        alert(msg);

        this.error = msg;
        this.loading = false;
        this.stays = [];

        this.cd.detectChanges();
      }
    });
  }

  // 🔄 CLEAR DATA WHEN INPUT CHANGES
  onSsnChange() {
    if (this.hasSearched) {
      this.stays = [];
      this.error = '';
      this.loading = false;
      this.hasSearched = false;

      this.cd.detectChanges();
    }
  }
}