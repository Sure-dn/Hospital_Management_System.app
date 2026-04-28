import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-procedures-get',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './procedures-code.html',
  styleUrl: './procedures-code.css'
})
export class ProceduresCodeComponent {

  code: string = '';
  procedure: any = null;
  error: string = '';
  loading = false;
  hasSearched = false; // 🔥 ADD THIS

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  fetch() {

    // 🔥 RESET STATE
    this.procedure = null;
    this.error = '';
    this.loading = true;
    this.hasSearched = true;

    const token = localStorage.getItem('token');

    this.http.get<any>(
      `http://localhost:9090/api/procedures/${this.code}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    ).subscribe({
      next: (res) => {
        console.log("RESPONSE:", res);

        // 🔥 NEW REFERENCE
        this.procedure = { ...res.data };

        this.loading = false;
        this.cd.detectChanges(); // 🔥 FIX LOADING ISSUE
      },
      error: (err) => {
        console.log(err);

        let message = "Something went wrong";

        if (typeof err.error === 'string') {
          message = err.error;
        } else if (err.error?.message) {
          message = err.error.message;
        }

        alert(err.error.message);

        this.error = message;
        this.procedure = null;
        this.loading = false;

        this.cd.detectChanges(); // 🔥 IMPORTANT
      }
    });
  }

  // 🔥 SAME PATTERN AS OTHER COMPONENTS
  onCodeChange() {
    if (this.hasSearched) {
      this.procedure = null;
      this.error = '';
      this.loading = false;
      this.hasSearched = false;

      this.cd.detectChanges();
    }
  }
}