import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, CommonModule } from '@angular/common';

@Component({
  selector: 'app-block-rooms',
  standalone: true,
  imports: [FormsModule,JsonPipe, CommonModule],
  templateUrl: './floorcoderoom-get.html'
})
export class BlockRoomsComponent {

  floor!: number;
  code!: number;
  data: any;
  errorMsg = '';

  constructor(private http: HttpClient) {}

getRooms() {

  this.data = null;
  this.errorMsg = '';

  // ✅ FRONTEND VALIDATION
  if (!this.floor || !this.code) {
    this.errorMsg = "Floor and Code are required";
    alert("❌ " + this.errorMsg);
    return;
  }

  this.http.get(`http://localhost:9090/api/blocks/${this.floor}/${this.code}/rooms`)
    .subscribe({

      // ✅ SUCCESS
      next: (res: any) => {

        console.log("SUCCESS 👉", res);

        this.data = res;

        // 🔥 SUCCESS ALERT
        alert("✅ Rooms fetched successfully");

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
          msg = "Something went wrong";
        }

        this.errorMsg = msg;

        alert("❌ " + msg);
      }
    });
}

  }
