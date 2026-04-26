import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-get-by-date',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './prescriptions-datefromto-get.html'
})
export class GetByDateComponent {

  from: any;
  to: any;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:9090/api/prescriptions?from=${this.from}&to=${this.to}`)
      .subscribe(res => this.data = res);
  }
}