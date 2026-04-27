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

  code: string = '';
  procedure: any = null;
  error: string = '';
  loading = false;

  constructor(private http: HttpClient) {}

  fetch() {

  // RESET STATE FIRST 🔥
  this.procedure = null;
  this.error = '';
  this.loading = true;

  const token = localStorage.getItem('token');

  this.http.get<any>(
    `http://localhost:9090/api/procedures/${this.code}`,
    {
      headers: {
        Authorization: `Bearer ${token}`
      }
    }
  ).subscribe({
    next: (res) => {
      console.log("RESPONSE:", res);

      // Force new reference (VERY IMPORTANT)
      this.procedure = { ...res.data };

      this.loading = false;
    },
   error: (err) => {
  console.log(err);

  const message = err.error || err.message || "Something went wrong";

  alert(message); // ✅ popup alert

  this.procedure = null;
  this.loading = false;
  }
  });
}
}