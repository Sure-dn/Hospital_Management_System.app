import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-patient-putbypcp',
  standalone: true,
  imports: [FormsModule, JsonPipe,NgIf],
  templateUrl: './patient-putbyphysician.html',
  styleUrls: ['./patient-putbyphysician.css']
})
export class PatientPutPcpComponent {

  ssn!: number;
  physicianId!: number;
  data: any;

  constructor(private http: HttpClient) {}

  update() {

    console.log("SSN 👉", this.ssn);
    console.log("Physician 👉", this.physicianId);

    if (!this.ssn || !this.physicianId) {
      alert("❌ Please enter both SSN and Physician ID");
      return;
    }

    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`
    };

    this.http.put(
      `http://localhost:9090/api/patients/${this.ssn}/pcp/${this.physicianId}`,
      {},
      { headers }
    )
    .subscribe({
      next: (res: any) => {
        console.log("RESPONSE 👉", res);
        this.data = res;

        alert("✅ Patient PCP updated successfully!");
      },
      error: (err) => {
        console.error(err);
        alert(err.error?.message || "❌ Failed to update PCP!");
      }
    });
  }
}