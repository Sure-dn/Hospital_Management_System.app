import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-appointment-getbyid',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './appointment-getbyid.html',
  styleUrls: ['./appointment-getbyid.css']
})
export class AppointmentGetByIdComponent {

  id: number = 0;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:8080/api/appointments/${this.id}`)
      .subscribe(res => this.data = res);
  }
}