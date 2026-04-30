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
  private currentRequestCode: number | null = null;

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  load() {

    // 🔴 INPUT VALIDATION
    const procedureCode = Number(this.code);

    if (!this.code || isNaN(procedureCode) || procedureCode <= 0) {
      alert("Please enter a valid Procedure ID");
      return;
    }

    this.error = '';
    this.data = [];
    this.hasSearched = true;
    this.loading = true;

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
        if (this.currentRequestCode === procedureCode) {

          console.log("DATA:", res);

          this.data = [...res];
          this.loading = false;

          // 🔥 NO DATA → ALERT ONLY
          if (this.data.length === 0) {
            alert(`No patients found for procedure ID ${procedureCode}`);
            this.error = ''; // ❌ avoid duplicate UI message
          }

          this.cd.detectChanges();
        }
      },

      error: (err) => {
        if (this.currentRequestCode === procedureCode) {

          console.error("FULL ERROR:", err);

          let message = "Something went wrong";

          if (typeof err.error === 'string') {
            message = err.error;
          } else if (err.error?.message) {
            message = err.error.message;
          }

          alert(message); // 🔥 BACKEND ERROR ALERT

          this.error = message;
          this.loading = false;
          this.data = [];

          this.cd.detectChanges();
        }
      }
    });
  }

  // 🔄 CLEAR ON INPUT CHANGE
  onCodeChange() {
    if (this.hasSearched) {
      this.data = [];
      this.error = '';
      this.hasSearched = false;
      this.loading = false;
      this.cd.detectChanges();
    }
  }
}