import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-block-rooms',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './floorcoderoom-get.html',
  styleUrls: ['./floorcoderoom-get.css']
})
export class BlockRoomsComponent {

  floor!: number;
  code!: number;

  rooms: any[] = [];   // ✅ direct array (important)
  errorMsg = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef   // ✅ Injected
  ) {}

  getRooms() {

    this.rooms = [];
    this.errorMsg = '';

    if (!this.floor || !this.code) {
      this.errorMsg = "Floor and Code are required";
      this.cdr.detectChanges(); // 🔥 force update
      return;
    }

    this.http.get(`http://localhost:9090/api/blocks/${this.floor}/${this.code}/rooms`)
      .subscribe({

        // ✅ SUCCESS
        next: (res: any) => {

          console.log("SUCCESS 👉", res);

          this.rooms = res.data || [];

          if (this.rooms.length === 0) {
            this.errorMsg = "No rooms found";
          }

          // 🔥 Force UI refresh
          this.cdr.detectChanges();
        },

        // ❌ ERROR
        error: (err) => {

          console.log("ERROR 👉", err);

          this.errorMsg = err.error?.message || "Something went wrong";

          // 🔥 Force UI refresh
          this.cdr.detectChanges();
        }
      });
  }
}