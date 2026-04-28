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

        // 🔥 handle both wrapped & direct response
        this.treatments = Array.isArray(res) ? res : (res.data || []);

        this.loading = false;

        if (this.treatments.length === 0) {
          this.error = 'No treatments found';
        } else {
          this.error = '';
        }

        this.cd.detectChanges(); // 🔥 ensure UI updates
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
      this.loading = false;
      this.hasSearched = false;

      this.cd.detectChanges();
    }
  }
}