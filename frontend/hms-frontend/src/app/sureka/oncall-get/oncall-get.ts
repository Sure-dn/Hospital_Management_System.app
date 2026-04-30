import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-oncall-get',
  standalone: true,
  imports: [FormsModule, NgIf,NgFor],
  templateUrl: './oncall-get.html',
  styleUrls: ['./oncall-get.css']
})
export class OnCallGetComponent {

  employeeId!: number;

  // 🔥 ResponseStructure format
  data: any = { data: [] };

  errorMsg: string = '';

  constructor(private http: HttpClient) {}

  getByNurse() {

    this.errorMsg = '';
    this.data = { data: [] };

    // ✅ Validation
    if (!this.employeeId) {
      alert("❌ Employee ID is required");
      return;
    }

    this.http.get(`http://localhost:9090/api/nurses/${this.employeeId}/on-call`)
      .subscribe({

        // ✅ SUCCESS
        next: (res: any) => {
          console.log("API RESPONSE 👉", res);

          this.data = res;

          // 🔴 No records case
          if (!this.data.data || this.data.data.length === 0) {
            this.errorMsg = "No records found";
          }
        },

        // ❌ ERROR (SHOW BACKEND MESSAGE)
        error: (err) => {

          console.log("FULL ERROR 👉", err);

          let msg = '';

          if (err.error?.message) {
            msg = err.error.message; // 🔥 backend exception
          } else if (typeof err.error === 'string') {
            msg = err.error;
          } else {
            msg = "Something went wrong";
          }

          this.errorMsg = msg;

          // 🔥 ALERT
          alert("❌ " + msg);
        }
      });
  }
}