import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-appointment-getbyphysicianid',
  standalone: true,
  imports: [FormsModule,JsonPipe],
  templateUrl: './appointment-getbyphysicianid.html',
  styleUrls: ['./appointment-getbyphysicianid.css']
})
export class AppointmentGetByPhysicianComponent {

  physicianId: number = 0;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:8080/api/appointments/physician/${this.physicianId}`)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}