import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NgFor, NgIf} from '@angular/common';

@Component({
  selector: 'app-appointment-get-all',
  standalone: true,
  imports: [NgFor, NgIf],
  templateUrl: './appointment-get.html',
  styleUrl: './appointment-get.css'
})
export class AppointmentGetAllComponent {

  data: any[] = [];
  error = '';

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('Iniya:123')
    });
  }

  getAll() {
    this.error = '';

    this.http.get<any[]>('http://localhost:9090/api/appointments', {
      headers: this.getHeaders()
    }).subscribe({
      next: (res) => {
        this.data = res;
      },
      error: (err) => {
        this.error = err.error?.message || '❌ Failed to fetch appointments';
        alert(this.error);
      }
    });
  }
}