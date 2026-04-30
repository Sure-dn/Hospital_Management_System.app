import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-nurse-by-id',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './employeeid-get.html',
  styleUrls: ['./employeeid-get.css']
})
export class GetNurseByIdComponent {

  id: string = '';        // use string (avoids number input issues)
  nurse: any = null;      // store only inner data
  errorMsg: string = '';

  constructor(private http: HttpClient) {}

  getById() {

    this.errorMsg = '';
    this.nurse = null;

    // ✅ validation
    if (!this.id) {
      alert("❌ Employee ID is required");
      return;
    }

    this.http.get(`http://localhost:9090/api/nurses/${this.id}`)
      .subscribe({

        next: (res: any) => {
          console.log("API RESPONSE 👉", res);

          // 🔥 IMPORTANT: take only res.data
          this.nurse = res.data;
        },

        error: (err) => {
          let msg = err.error?.message || "Something went wrong";
          this.errorMsg = msg;
          alert("❌ " + msg);
        }
      });
  }
}