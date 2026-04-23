import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-stay-get',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './stay-get.html',
  styleUrl: './stay-get.css'
})
export class StayGetComponent {

  stayId: any;
  data: any;
  error: string = '';

  constructor(private http: HttpClient) {}

  fetch() {
    this.error = '';
    this.data = null;

    this.http.get(`http://localhost:9090/api/stays/${this.stayId}`)
      .subscribe({
        next: (res: any) => {
          this.data = res.data || res; // handle wrapped response
        },
        error: () => {
          this.error = "❌ Stay not found";
        }
      });
  }
}