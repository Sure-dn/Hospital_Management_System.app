import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-get-by-patient',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './prescriptions-patientssn-get.html'
})
export class GetByPatientComponent {

  ssn: any;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:9090/api/prescriptions/patient/${this.ssn}`)
      .subscribe(res => this.data = res);
  }
}