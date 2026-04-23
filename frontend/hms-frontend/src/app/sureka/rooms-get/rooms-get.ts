import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-rooms-get',
  standalone: true,
  imports: [JsonPipe],
  templateUrl: './rooms-get.html'
})
export class RoomsGetComponent {

  data: any;

  constructor(private http: HttpClient) {}

  getRooms() {
    this.http.get('http://localhost:9090/api/rooms')
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}
