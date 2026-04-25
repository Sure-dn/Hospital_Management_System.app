import { JsonPipe } from '@angular/common';
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-patient-get',
  standalone: true,
  imports: [JsonPipe],
  templateUrl: './patient-get.html',
  styleUrl: './patient-get.css',
})
export class PatientGet {
    data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get('http://localhost:8080/api/patients')
      .subscribe(res => this.data = res);
  }
}

