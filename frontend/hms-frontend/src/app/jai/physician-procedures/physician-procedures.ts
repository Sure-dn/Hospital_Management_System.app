import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor ,DatePipe} from '@angular/common';

@Component({
  selector: 'app-physician-procedures',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, DatePipe],
  templateUrl: './physician-procedures.html',
  styleUrl: './physician-procedures.css'
})
export class PhysicianProceduresComponent {

  physicianId: any;
  procedures: any[] = [];
  error: string = '';
  hasSearched = false;

  constructor(private http: HttpClient) {}

  loading = false;

fetch() {
  this.hasSearched = true;
  this.error = '';
  this.loading = true;

  const token = localStorage.getItem('token');

  this.http.get<any[]>(
    `http://localhost:9090/api/reports/physician/${this.physicianId}/procedures`,
    {
      headers: {
        Authorization: 'Bearer ' + token
      }
    }
  ).subscribe({
    next: (res) => {
      console.log("DATA:", res);
      this.procedures = res;
      this.loading = false;
    },
    error: (err) => {
      console.error(err);
      this.error = err.error?.message || 'Unauthorized ❌';
      this.loading = false;
    }
  });
}
}