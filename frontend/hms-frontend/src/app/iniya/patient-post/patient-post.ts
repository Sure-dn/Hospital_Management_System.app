import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-patient-post',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './patient-post.html',
  styleUrl: './patient-post.css'
})
export class PatientPostComponent {

  ssn: any;
  name: any;
  address: any;
  phone: any;
  insuranceId: any;
  physicianId: any;

  data: any;
  success = '';
  error = '';

  constructor(private http: HttpClient) {}

  create() {
    this.success = '';
    this.error = '';
    this.data = null;

    // ✅ FIXED PAYLOAD
    const payload = {
      ssn: Number(this.ssn),
      name: this.name,
      address: this.address,
      phone: this.phone,
      insuranceId: Number(this.insuranceId),
      pcp: Number(this.physicianId) 
    };

    console.log("PAYLOAD:", payload);

    this.http.post('http://localhost:9090/api/patients', payload)
      .subscribe({
        next: (res: any) => {
          console.log("SUCCESS:", res);
          this.data = res.data || res;
          this.success = "✅ Patient created successfully";
        },
        error: (err) => {
          console.log("FULL ERROR:", err);
          console.log("BACKEND ERROR:", err.error);

          this.error = err.error?.message || "❌ Failed to create patient details";
        }
      });
  }
}