import { ChangeDetectorRef, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor, DatePipe } from '@angular/common';

@Component({
  selector: 'app-physician-procedures',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, DatePipe],
  templateUrl: './physician-procedures.html',
  styleUrl: './physician-procedures.css'
})
export class PhysicianProceduresComponent {

  physicianId: any;
  procedures: any[] = [];
  error: string = '';
  hasSearched = false;
  loading = false;

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  fetch() {

    // 🔴 1. EMPTY INPUT VALIDATION
    if (!this.physicianId) {
      alert("Physician ID should not be empty");
      return;
    }

    this.hasSearched = true;
    this.error = '';
    this.loading = true;

    // 🔥 RESET OLD DATA
    this.procedures = [];

    const token = localStorage.getItem('token');

    this.http.get<any[]>(
      `http://localhost:9090/api/reports/physician/${this.physicianId}/procedures`,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({

      next: (res) => {
        console.log("DATA:", res);

        this.procedures = [...res];
        this.loading = false;

        // 🔴 2. NO DATA → ALERT
        if (this.procedures.length === 0) {
          alert(`No procedures found with this physician ID ${this.physicianId}`);
          this.error = ''; // ❌ avoid duplicate UI message
        }

        this.cd.detectChanges();
      },

      error: (err) => {
        console.error(err);

        let errorMsg = 'Something went wrong';

        if (typeof err.error === 'string') {
          errorMsg = err.error;
        } else if (err.error?.message) {
          errorMsg = err.error.message;
        } else if (err.error?.error) {
          errorMsg = err.error.error;
        } else if (err.message) {
          errorMsg = err.message;
        }

        // 🔴 3. BACKEND ERROR ALERT
        alert(errorMsg);

        this.error = errorMsg;
        this.loading = false;
        this.procedures = [];

        this.cd.detectChanges();
      }
    });
  }

  // 🔄 CLEAR ON INPUT CHANGE
  onIdChange() {
    if (this.hasSearched) {
      this.procedures = [];
      this.error = '';
      this.hasSearched = false;
      this.loading = false;

      this.cd.detectChanges();
    }
  }
}