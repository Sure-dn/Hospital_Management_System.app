import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-nurse-by-id',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './employeeid-get.html'
})
export class GetNurseByIdComponent {

  id!: number;
  data: any;
  errorMsg = '';

  constructor(private http: HttpClient) {}

  getById() {

    this.errorMsg = '';
    this.data = null;

    // ✅ Frontend validation
    if (!this.id) {
      this.errorMsg = "Employee ID is required";
      alert("❌ " + this.errorMsg);
      return;
    }

    this.http.get(`http://localhost:9090/api/nurses/${this.id}`)
      .subscribe({

        // ✅ SUCCESS
        next: (res: any) => {
          this.data = res;
        },

        // ❌ ERROR (Backend Exception)
        error: (err) => {

          console.log("FULL ERROR 👉", err);

          let msg = '';

          // 🔥 Backend exception message
          if (err.error?.message) {
            msg = err.error.message;
          }

          // 🔥 Plain string error
          else if (typeof err.error === 'string') {
            msg = err.error;
          }

          // 🔥 Fallback
          else {
            msg = "Nurse not found";
          }

          this.errorMsg = msg;

          // ✅ Popup alert
          alert("❌ " + msg);
        }
      });
  }
}