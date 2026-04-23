import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-block-rooms',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './floorcoderoom-get.html'
})
export class BlockRoomsComponent {

  floor!: number;
  code!: number;
  data: any;

  constructor(private http: HttpClient) {}

  getRooms() {
    this.http.get(`http://localhost:9090/api/blocks/${this.floor}/${this.code}/rooms`)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}
