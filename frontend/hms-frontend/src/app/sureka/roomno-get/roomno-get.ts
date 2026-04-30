import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-room-get',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './roomno-get.html',
  styleUrl:'./roomno-get.css'
})
export class RoomGetComponent {

  roomNumber!: number;
  data: any = null;

  constructor(private http: HttpClient) {}

  getRoom() {

    this.data = null;

    if (!this.roomNumber) {
      alert("❌ Room Number required");
      return;
    }

    this.http.get(`http://localhost:9090/api/rooms/${this.roomNumber}`)
      .subscribe({
        next: (res: any) => {
          console.log("API RESPONSE 👉", res);
          this.data = res; // 🔥 contains data.data
        },
        error: (err) => {
          alert("❌ " + (err.error?.message || "Room not found"));
        }
      });
  }
}