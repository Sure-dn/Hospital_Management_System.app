import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-create-prescription',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './prescriptions-post.html'
})
export class CreatePrescriptionComponent {

  data: any = {};
  response: any;

  constructor(private http: HttpClient) {}

  create() {
    this.http.post('http://localhost:9090/api/prescriptions', this.data)
      .subscribe(res => this.response = res);
  }
}