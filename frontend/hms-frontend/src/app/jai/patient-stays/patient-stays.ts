import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-patient-stays',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './patient-stays.html',
  styleUrl: './patient-stays.css'
})
export class PatientStaysComponent {

  ssn: any;
  stays: any[] = [];
  error = '';

  constructor(private http: HttpClient) {}

  load() {
    this.error = '';
    this.stays = [];

    this.http.get<any[]>(`http://localhost:9090/api/patients/${this.ssn}/stays`)
      .subscribe({
        next: (res) => {
          this.stays = res;
        },
        error: (err) => {
          console.error(err);
          this.error = 'Failed to fetch stays ❌';
        }
      });
  }
}