import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-get-all-medications',
  standalone: true,
  imports: [JsonPipe],
  templateUrl: './medications-get.html'
})
export class GetAllMedicationsComponent {

  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get('http://localhost:9090/api/medications')
      .subscribe(res => this.data = res);
  }
}