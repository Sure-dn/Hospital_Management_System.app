import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-stay-treatments',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './stay-treatments.html',
  styleUrl: './stay-treatments.css'
})
export class StayTreatmentsComponent {

  stayId: any;
  treatments: any[] = [];
  error = '';

  constructor(private http: HttpClient) {}

  load() {
    this.error = '';
    this.treatments = [];

    this.http.get<any>(`http://localhost:9090/api/stays/${this.stayId}/treatments`)
      .subscribe({
        next: (res) => {
          // If your backend wraps response → use res.data
          this.treatments = res.data || res;
        },
        error: (err) => {
          console.error(err);
          this.error = 'Failed to fetch treatments ❌';
        }
      });
  }
}