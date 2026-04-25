import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-appointment-get',
  standalone: true,
  imports: [JsonPipe],
  templateUrl: './appointment-get.html',
  styleUrls: ['./appointment-get.css']
})
export class AppointmentGetComponent {

  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get('http://localhost:8080/api/appointments')
      .subscribe(res => this.data = res);
  }
}