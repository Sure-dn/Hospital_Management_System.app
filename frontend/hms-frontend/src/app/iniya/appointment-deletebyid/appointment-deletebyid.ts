import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-appointment-delete',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './appointment-deletebyid.html',
  styleUrls: ['./appointment-deletebyid.css']
})
export class AppointmentDeleteComponent {

  id: number = 0;
  message: string = '';

  constructor(private http: HttpClient) {}

  delete() {
    this.http.delete(`http://localhost:8080/api/appointments/${this.id}`)
      .subscribe(() => this.message = "Deleted Successfully");
  }
}