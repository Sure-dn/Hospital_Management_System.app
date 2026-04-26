import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-patient-get-all',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './patient-get.html',
  styleUrl: './patient-get.css'
})
export class PatientGetAllComponent {

  data: any[] = [];
  error = '';
  success = '';

  constructor(private http: HttpClient) {}

  fetchAll() {
    this.error = '';
    this.success = '';
    this.data = [];

    this.http.get('http://localhost:9090/api/patients')
      .subscribe({
        next: (res: any) => {
          console.log("FULL RESPONSE:", res);

          // ✅ handle both array and wrapped response
          if (Array.isArray(res)) {
            this.data = res;
          } else if (res.data) {
            this.data = res.data;
          } else {
            this.data = [];
          }

          console.log("ASSIGNED DATA:", this.data);

          this.success = "✅ Patients fetched successfully";
          setTimeout(() => this.success = '', 3000);
        },
        error: (err) => {
          console.log("ERROR:", err);
          this.error = "❌ Failed to fetch patients";
          setTimeout(() => this.error = '', 3000);
        }
      });
  }
}