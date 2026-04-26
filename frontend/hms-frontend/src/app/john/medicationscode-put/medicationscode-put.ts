import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-update-medication',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './medicationscode-put.html'
})
export class UpdateMedicationComponent {

  code: any;
  data: any = {};
  response: any;

  constructor(private http: HttpClient) {}

 update() {

  const payload: any = {};

  if (this.data.name) payload.name = this.data.name;
  if (this.data.brand) payload.brand = this.data.brand;
  if (this.data.description) payload.description = this.data.description;

  this.http.put(`http://localhost:9090/api/medications/${this.code}`, payload)
    .subscribe({
      next: (res) => {
        this.response = res;
        alert("✅ Medication updated successfully!");
      },
      error: (err) => {
        console.error(err);

        if (err.status === 404) {
          alert("❌ Medication not found!");
        } else if (err.status === 400) {
          alert("⚠️ Invalid input data!");
        } else if (err.status === 401) {
          alert("🔐 Unauthorized! Please login again.");
        } else {
          alert("❌ Something went wrong!");
        }
      }
    });
}
}