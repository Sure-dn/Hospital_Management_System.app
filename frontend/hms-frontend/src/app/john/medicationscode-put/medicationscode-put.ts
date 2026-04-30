import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-update-medication',
  standalone: true,
  imports: [FormsModule, JsonPipe, NgIf],
  templateUrl: './medicationscode-put.html'
})
export class UpdateMedicationComponent {

  code: any;
  data: any = {};
  response: any;
  errorMsg: string = '';

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

 update() {
  this.errorMsg = '';
  const payload: any = {};

  if (this.data.name) payload.name = this.data.name;
  if (this.data.brand) payload.brand = this.data.brand;
  if (this.data.description) payload.description = this.data.description;

  this.http.put(`http://localhost:9090/api/medications/${this.code}`, payload)
    .subscribe({
      next: (res) => {
        this.response = res;
        this.cdr.detectChanges();
        alert("✅ Medication updated successfully!");
      },
      error: (err) => {
        this.errorMsg = (typeof err.error === 'string' && err.error.trim()) ? err.error.trim() : `Error \${err.status}: \${err.statusText || err.message}`;
        this.cdr.detectChanges();
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