import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-training-valid',
  imports: [FormsModule,CommonModule,JsonPipe],
  templateUrl: './training-valid.html',
  styleUrl: './training-valid.css',
})
export class TrainingValid {
  employeeId: string = '';
  result: any;

  constructor(private http: HttpClient) {}

  fetchValidTrainings() {
    if (!this.employeeId) {
      alert('Please enter Employee ID');
      return;
    }

    this.http.get(`http://localhost:9090/api/physicians/${this.employeeId}/trainings/valid`)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Error while fetching valid trainings');
          console.error(err);
        }
      });
  }
}
