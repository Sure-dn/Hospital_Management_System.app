import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-room-update',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './roomnoavail-put.html'
})
export class RoomUpdateComponent {

  roomNumber!: number;
  unavailable!: boolean;
  data: any;

  constructor(private http: HttpClient) {}

  update() {
    this.http.put(
      `http://localhost:9090/api/rooms/${this.roomNumber}/availability?unavailable=${this.unavailable}`,
      {}
    ).subscribe({
      next: res => this.data = res,
      error: err => console.error(err)
    });
  }
}
