import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-appointment-getbyssn',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './appointment-getbyssn.html',
  styleUrls: ['./appointment-getbyssn.css']
})
export class AppointmentGetBySsnComponent {

  ssn: number = 0;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:8080/api/appointments/patient/${this.ssn}`)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}