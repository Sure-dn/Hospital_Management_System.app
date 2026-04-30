import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-rooms-get',
  standalone: true,
  imports: [CommonModule], // 🔥 IMPORTANT FIX
  templateUrl: './rooms-get.html',
  styleUrls: ['./rooms-get.css']
})
export class RoomsGetComponent {

  rooms: any[] = [];
  errorMsg = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  getRooms() {

    this.rooms = [];
    this.errorMsg = '';

    this.http.get('http://localhost:9090/api/rooms')
      .subscribe({
        next: (res: any) => {

          console.log("SUCCESS 👉", res);

          this.rooms = [...(res.data || [])]; // 🔥 forces new reference

          console.log("ROOMS 👉", this.rooms); // 🔥 CHECK THIS

          if (this.rooms.length === 0) {
            this.errorMsg = "No rooms found";
          }

          this.cdr.detectChanges();
        },

        error: (err) => {
          this.errorMsg = err.error?.message || "Something went wrong";
          this.cdr.detectChanges();
        }
      });
  }
}