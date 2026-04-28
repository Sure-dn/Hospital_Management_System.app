import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-patient-get-all',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './patient-get.html',
  styleUrl: './patient-get.css'
})
export class PatientGetAllComponent {

  data: any[] = [];

  constructor(private http: HttpClient) {}

  fetchAll() {
    this.http.get('http://localhost:9090/api/patients')
      .subscribe({
        next: (res: any) => {
          console.log("RESPONSE:", res);

          // ✅ SIMPLE FIX
          this.data = res.data ? res.data : res;

          alert("✅ Patients fetched successfully");
        },
        error: (err) => {
          console.log(err);
          alert("❌ Failed to fetch patients");
        }
      });
  }
}