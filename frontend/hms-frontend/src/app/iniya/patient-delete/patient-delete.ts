import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-patient-delete',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './patient-delete.html',
  styleUrls: ['./patient-delete.css']
})
export class PatientDeleteComponent {

  ssn: number = 0;
  message: string = '';

  constructor(private http: HttpClient) {}

  delete() {
    this.http.delete(`http://localhost:8080/api/patients/${this.ssn}`)
      .subscribe(() => this.message = "Deleted Successfully");
  }
}