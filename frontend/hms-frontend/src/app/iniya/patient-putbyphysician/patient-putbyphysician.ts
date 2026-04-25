import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-patient-putbypcp',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './patient-putbyphysician.html',
  styleUrls: ['./patient-putbyphysician.css']
})
export class PatientPutPcpComponent {

  ssn: number = 0;
  physicianId: number = 0;
  data: any;

  constructor(private http: HttpClient) {}

  update() {
    this.http.put(`http://localhost:8080/api/patients/${this.ssn}/pcp/${this.physicianId}`, {})
      .subscribe(res => this.data = res);
  }
}
