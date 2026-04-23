import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-procedure-patients',
  standalone: true,
  imports: [FormsModule, JsonPipe, NgIf, NgFor],
  templateUrl: './procedure-patients.html',
  styleUrl: './procedure-patients.css'
})
export class ProcedurePatientsComponent {

  code: any;
  data: any[] = [];
  error: string = '';

  constructor(private http: HttpClient) {}

  load() {
    this.error = '';
    this.data = [];

    this.http.get<any[]>(`http://localhost:9090/api/procedures/${this.code}/patients`)
      .subscribe({
        next: (res) => {
          this.data = res;
        },
        error: (err) => {
          console.error(err);
          this.error = 'Failed to fetch patients';
        }
      });
  }
}