import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-treatment-get',
  standalone: true,
  imports: [NgIf, NgFor],
  templateUrl: './treatment-get.html',
  styleUrl: './treatment-get.css'
})
export class TreatmentGetComponent {

  treatments: any[] = [];
  error = '';

  constructor(private http: HttpClient) {}

  load() {
    this.error = '';
    this.treatments = [];

    this.http.get<any[]>('http://localhost:9090/api/treatments')
      .subscribe({
        next: (res) => {
          this.treatments = res;
        },
        error: (err) => {
          console.error(err);
          this.error = err.error?.message || 'Failed to fetch treatments ❌';
          alert(err.error?.message || 'Failed to fetch treatments ❌'); // 🔥 popup
        }
      });
  }
}