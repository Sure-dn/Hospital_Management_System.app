import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-patient-delete',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './patient-delete.html',
  styleUrl:'./patient-delete.css'
})
export class PatientDeleteComponent {

  ssn!: number;
  data: any;

  constructor(private http: HttpClient) {}

  delete() {

    console.log("SSN 👉", this.ssn);

    if (!this.ssn) {
      alert("❌ Please enter SSN");
      return;
    }

    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    this.http.delete(
      `http://localhost:9090/api/patients/${this.ssn}`,
      { headers }
    ).subscribe({
      next: (res) => {
        console.log("Response 👉", res);
        this.data = res;

        alert("✅ Patient deleted successfully!");

        // reset input
        this.ssn = 0;
      },
      error: (err) => {
        console.error(err);

        alert(err.error?.message || "❌ Failed to delete patient!");
      }
    });
  }
}