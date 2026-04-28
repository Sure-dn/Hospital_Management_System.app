import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-training-valid',
  imports: [FormsModule, CommonModule, JsonPipe],
  templateUrl: './training-valid.html',
  styleUrl: './training-valid.css',
})
export class TrainingValid {

  employeeId: string = '';
  result: any = null;
  error = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  fetchValidTrainings() {

    this.error = '';
    this.result = null;

    if (!this.employeeId) {
      this.error = 'All fields are required';
      setTimeout(() => alert(this.error), 300);
      return;
    }

    this.http.get(`http://localhost:9090/api/physicians/${this.employeeId}/trainings/valid`)
      .subscribe({
        next: (res: any) => {

          
          this.zone.run(() => {
            this.result = res;
            this.cd.detectChanges();
          });

        },
        error: () => {
          alert('Error while fetching valid trainings');
        }
      });
  }
}