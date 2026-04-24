import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-room-get',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './roomno-get.html'
})
export class RoomGetComponent {

  roomNumber!: number;
  data: any;

  constructor(private http: HttpClient) {}

  getRoom() {
    this.http.get(`http://localhost:9090/api/rooms/${this.roomNumber}`)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}
