import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf, NgFor, DatePipe } from '@angular/common';

@Component({
  selector: 'app-oncall-floor',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, DatePipe], // ✅ ADD NgFor
  templateUrl: './floorcode-get.html',
  styleUrl: './floorcode-get.css'
})
export class OnCallFloorComponent {

  floor!: number;
  code!: number;
  data: any;

  constructor(private http: HttpClient) {}

  getByBlock() {
    this.http.get(`http://localhost:9090/api/on-call?floor=${this.floor}&code=${this.code}`)
      .subscribe({
        next: res => {
          console.log("API Response:", res); // ✅ Debug
          this.data = res;
        },
        error: err => console.error(err)
      });
  }
}