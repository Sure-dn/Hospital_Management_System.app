import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-training-post',
  imports: [FormsModule, JsonPipe, CommonModule],
  templateUrl: './training-post.html',
  styleUrl: './training-post.css',
})
export class TrainingPost {

  employeeId: string = '';
  treatmentCode: string = '';
  certificationExpiry: string = '';
  result: any = null;

  error = '';

  constructor(private http: HttpClient) {}

  createTraining() {

    this.error = '';
    this.result = null;

    // ✅ simple validation
    if (!this.employeeId || !this.treatmentCode || !this.certificationExpiry) {
      this.error = 'All fields are required';

      setTimeout(() => {
        alert(this.error);
      }, 300);

      return;
    }

    const body = {
      treatmentCode: Number(this.treatmentCode),
      certificationExpiry: this.certificationExpiry
    };

    this.http.post(
      `http://localhost:9090/api/physicians/${this.employeeId}/trainings`,
      body
    ).subscribe({
      next: (res: any) => {
        this.result = res;
      },
      error: () => {
        alert('Error while creating training');
      }
    });
  }
}