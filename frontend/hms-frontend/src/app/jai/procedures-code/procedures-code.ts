import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-procedures-get',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './procedures-code.html',
  styleUrl: './procedures-code.css'
})
export class ProceduresCodeComponent {

  code: any;
  procedure: any;

  constructor(private http: HttpClient) {}

  fetch() {
    this.http.get<any>(`http://localhost:9090/api/procedures/${this.code}`)
      .subscribe({
        next: (res) => {
          this.procedure = res.data; // ⚠ based on your backend response
        },
        error: () => {
          alert("Procedure not found ❌");
          this.procedure = null;
        }
      });
  }
}