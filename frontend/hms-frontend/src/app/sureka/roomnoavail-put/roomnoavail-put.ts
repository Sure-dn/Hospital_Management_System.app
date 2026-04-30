import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe ,NgIf} from '@angular/common';

@Component({
  selector: 'app-room-update',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './roomnoavail-put.html',
  styleUrl: './roomnoavail-put.css'
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
