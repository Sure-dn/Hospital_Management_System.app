import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-patient-put',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './patient-put.html',
  styleUrls: ['./patient-put.css']
})
export class PatientPutComponent {

  patient: any = {};
  data: any;

  constructor(private http: HttpClient) {}

  update() {
    this.http.put(`http://localhost:8080/api/patients/${this.patient.ssn}`, this.patient)
      .subscribe(res => this.data = res);
  }
}