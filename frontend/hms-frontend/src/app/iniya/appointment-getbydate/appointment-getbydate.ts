import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-appointment-getbydate',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './appointment-getbydate.html',
  styleUrls: ['./appointment-getbydate.css']
})
export class AppointmentGetByDateComponent {

  date: string = '';
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:9090/api/appointments/by-date?date=${this.date}`)
      .subscribe(res => this.data = res);
  }
}