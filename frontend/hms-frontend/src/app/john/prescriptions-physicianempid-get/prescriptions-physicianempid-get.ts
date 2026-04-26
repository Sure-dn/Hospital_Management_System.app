import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-get-by-physician',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './prescriptions-physicianempid-get.html'
})
export class GetByPhysicianComponent {

  id: any;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:9090/api/prescriptions/physician/${this.id}`)
      .subscribe(res => this.data = res);
  }
}