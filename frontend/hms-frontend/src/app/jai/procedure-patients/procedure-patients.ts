import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor, DatePipe } from '@angular/common';

@Component({
  selector: 'app-procedure-patients',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, DatePipe],
  templateUrl: './procedure-patients.html',
  styleUrl: './procedure-patients.css'
})
export class ProcedurePatientsComponent {

  code: any;
  data: any[] = [];
  error: string = '';
  hasSearched = false;
  loading = false;
  private currentRequestCode: number | null = null; // Track current request

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  load() {
    // Reset all states when starting new search
    this.error = '';
    this.data = [];
    this.hasSearched = true;
    this.loading = true;

    // Validate input
    const procedureCode = Number(this.code);
    
    // Don't make request if code is invalid
    if (!this.code || isNaN(procedureCode) || procedureCode <= 0) {
      this.error = 'Please enter a valid Procedure ID (positive number)';
      this.loading = false;
      this.hasSearched = false;
      this.cd.detectChanges();
      return;
    }

    // Store current request code to prevent race conditions
    this.currentRequestCode = procedureCode;
    
    const token = localStorage.getItem('token');

    this.http.get<any[]>(
  `http://localhost:9090/api/reports/procedure/${procedureCode}/patients`,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({
      next: (res) => {
        // Only update if this is still the current request
        if (this.currentRequestCode === procedureCode) {
          console.log("DATA:", res);
          this.data = [...res];
          this.loading = false;
          
          // Clear error if successful
          if (this.data.length === 0) {
            this.error = 'No patients found for this procedure';
          } else {
            this.error = '';
          }
          
          this.cd.detectChanges();
        }
      },
      error: (err) => {
  if (this.currentRequestCode === procedureCode) {
    console.error("FULL ERROR:", err);

    let message = "Something went wrong";

    if (typeof err.error === 'string') {
      message = err.error; // ✅ backend message
    } else if (err.error?.message) {
      message = err.error.message;
    }

    alert(message); // ✅ popup alert

    this.error = message; // optional (if you still want UI text)
    this.loading = false;
    this.data = [];
    this.cd.detectChanges();
  }
}
    });
  }
  
  // Clear data when input changes
  onCodeChange() {
    // Clear previous results when user starts typing
    if (this.hasSearched) {
      this.data = [];
      this.error = '';
      this.hasSearched = false;
      this.loading = false;
      this.cd.detectChanges();
    }
  }
}