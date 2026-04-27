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

  const token = localStorage.getItem('token');

  this.http.get<any>(
    `http://localhost:9090/api/treatments/stay/${this.stayId}`,
    {
      headers: {
        Authorization: 'Bearer ' + token
      }
    }
  ).subscribe({
    next: (res) => {
      console.log(res);

      // ✅ correct mapping
      this.treatments = res.data || [];

    },
    error: (err) => {
      console.error(err);
      this.error = 'Failed to fetch treatments ❌';
    }
  });
}
}