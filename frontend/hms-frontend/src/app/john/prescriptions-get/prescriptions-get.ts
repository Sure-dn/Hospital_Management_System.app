import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-get-all-prescriptions',
  standalone: true,
  imports: [JsonPipe],
  templateUrl: './prescriptions-get.html'
})
export class GetAllPrescriptionsComponent {

  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get('http://localhost:9090/api/prescriptions')
      .subscribe(res => this.data = res);
  }
}