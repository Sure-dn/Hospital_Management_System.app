import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-patient-getpcp',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './patient-getbyphysician.html',
  styleUrls: ['./patient-getbyphysician.css']
})
export class PatientGetPcpComponent {

  ssn: number = 0;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:8080/api/patients/${this.ssn}/pcp`)
      .subscribe(res => this.data = res);
  }
}