import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-appointment-put',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './appointment-putbyid.html',
  styleUrls: ['./appointment-putbyid.css']
})
export class AppointmentPutComponent {

  appointment: any = {};
  data: any;

  constructor(private http: HttpClient) {}

  update() {
    this.http.put(`http://localhost:8080/api/appointments/${this.appointment.appointmentId}`, this.appointment)
      .subscribe(res => this.data = res);
  }
}