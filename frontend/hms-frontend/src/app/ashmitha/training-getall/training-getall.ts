import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-training-getall',
  imports: [FormsModule,CommonModule,JsonPipe],
  templateUrl: './training-getall.html',
  styleUrl: './training-getall.css',
})
export class TrainingGetall {
  employeeId: string = '';
  result: any;

  constructor(private http: HttpClient) {}

  fetchTrainings() {
    if (!this.employeeId) {
      alert('Please enter Employee ID');
      return;
    }

    this.http.get(`http://localhost:9090/api/physicians/${this.employeeId}/trainings`)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Error while fetching trainings');
          console.error(err);
        }
      });
  }
}
