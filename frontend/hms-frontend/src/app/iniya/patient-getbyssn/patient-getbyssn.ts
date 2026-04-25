import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-patient-getbyssn',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './patient-getbyssn.html',
  styleUrls: ['./patient-getbyssn.css']
})
export class PatientGetBySsnComponent {

  ssn: number = 0;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:8080/api/patients/${this.ssn}`)
      .subscribe(res => this.data = res);
  }
}