import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-patient-getpcp',
  standalone: true,
  imports: [FormsModule,NgIf],
  templateUrl: './patient-getbyphysician.html',
  styleUrls: ['./patient-getbyphysician.css']
})
export class PatientGetPcpComponent {

  ssn!: number;
  data: any;

  constructor(private http: HttpClient) {}

  load() {

    if (!this.ssn) {
      alert("❌ Please enter SSN");
      return;
    }

    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    this.http.get(
      `http://localhost:9090/api/patients/${this.ssn}/pcp`,
      { headers }
    )
    .subscribe({
      next: (res: any) => {
        console.log("RESPONSE 👉", res);
        this.data = res;

        alert("✅ PCP fetched successfully!");
      },
      error: (err) => {
        console.error(err);
        alert(err.error?.message || "❌ Failed to fetch PCP!");
      }
    });
  }
}