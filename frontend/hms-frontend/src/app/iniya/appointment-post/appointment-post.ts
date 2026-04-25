import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-appointment-post',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './appointment-post.html',
  styleUrls: ['./appointment-post.css']
})
export class AppointmentPostComponent {

  appointment: any = {};
  data: any;

  constructor(private http: HttpClient) {}

  submit() {
    this.http.post('http://localhost:8080/api/appointments', this.appointment)
      .subscribe(res => this.data = res);
  }
}