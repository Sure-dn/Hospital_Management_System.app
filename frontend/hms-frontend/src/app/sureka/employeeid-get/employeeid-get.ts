import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-nurse-by-id',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './employeeid-get.html',
  styleUrls: ['./employeeid-get.css'] // optional
})
export class GetNurseByIdComponent {

  id!: number;
  data: any;
  errorMsg = '';

  constructor(private http: HttpClient) {}

  getById() {

    this.errorMsg = '';
    this.data = null;

    // ✅ Validation
    if (!this.id) {
      this.errorMsg = "Employee ID is required";
      alert("❌ " + this.errorMsg);
      return;
    }

    this.http.get(`http://localhost:9090/api/nurses/${this.id}`)
      .subscribe({

        // ✅ SUCCESS
        next: (res: any) => {

          console.log("API RESPONSE 👉", res);

          // 🔥 IMPORTANT FIX
          this.data = res.data;

          if (!this.data) {
            this.errorMsg = "No nurse found";
          }
        },

        // ❌ ERROR
        error: (err) => {

          console.log("FULL ERROR 👉", err);

          let msg = '';

          if (err.error?.message) {
            msg = err.error.message;
          } 
          else if (typeof err.error === 'string') {
            msg = err.error;
          } 
          else {
            msg = "Nurse not found";
          }

          this.errorMsg = msg;
          alert("❌ " + msg);
        }
      });
  }
}