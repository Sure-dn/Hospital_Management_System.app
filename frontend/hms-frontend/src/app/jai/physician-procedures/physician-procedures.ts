import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-physician-procedures',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './physician-procedures.html',
  styleUrl: './physician-procedures.css'
})
export class PhysicianProceduresComponent {

  physicianId: any;
  procedures: any[] = [];
  error: string = '';
  hasSearched = false;

  constructor(private http: HttpClient) {}

  fetch() {
    this.hasSearched = true;
    this.error = '';
    this.procedures = [];

    this.http.get(`http://localhost:9090/api/physicians/${this.physicianId}/procedures`)
      .subscribe({
        next: (res: any) => {
          this.procedures = res.data || res;
        },
        error: () => {
          this.error = "❌ Failed to fetch procedures";
        }
      });
  }
}