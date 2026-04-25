import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-training-post',
  imports: [FormsModule,JsonPipe,CommonModule],
  templateUrl: './training-post.html',
  styleUrl: './training-post.css',
})
export class TrainingPost {
  employeeId: string = '';
  treatmentCode: string = '';
  certificationExpiry: string = '';
  result: any;

  constructor(private http: HttpClient) {}

  createTraining() {
    if (!this.employeeId || !this.treatmentCode || !this.certificationExpiry) {
      alert('Please enter all fields');
      return;
    }

    const body = {
      treatmentCode: Number(this.treatmentCode),
      certificationExpiry: this.certificationExpiry
    };

    this.http.post(`http://localhost:9090/api/physicians/${this.employeeId}/trainings`, body)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Error while creating training');
          console.error(err);
        }
      });
  }
}
