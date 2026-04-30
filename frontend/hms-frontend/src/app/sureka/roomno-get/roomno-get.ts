import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-room-get',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './roomno-get.html',
  styleUrls: ['./roomno-get.css']
})
export class RoomGetComponent {

  roomNumber!: number;
  room: any = null;
  errorMsg = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  getRoom() {

    this.room = null;
    this.errorMsg = '';

    if (!this.roomNumber) {
      this.errorMsg = "Room number is required";
      return;
    }

    this.http.get(`http://localhost:9090/api/rooms/${this.roomNumber}`)
      .subscribe({

        next: (res: any) => {

          console.log("SUCCESS 👉", res);

          // ✅ FIXED
          this.room = res.data;

          if (!this.room) {
            this.errorMsg = "Room not found";
          }

          this.cdr.detectChanges();
        },

        error: (err) => {
          console.error(err);
          this.errorMsg = err.error?.message || "Something went wrong";
          this.cdr.detectChanges();
        }
      });
  }
}