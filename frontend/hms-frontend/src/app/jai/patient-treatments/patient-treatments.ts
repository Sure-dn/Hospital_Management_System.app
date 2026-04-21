import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-patient-treatments',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './patient-treatments.html'
})
export class PatientTreatmentsComponent {

  id: any;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:9090/api/patients/${this.id}/treatments`)
      .subscribe(res => this.data = res);
  }
}