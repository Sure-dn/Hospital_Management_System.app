import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-update-medication',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './medicationscode-put.html'
})
export class UpdateMedicationComponent {

  code: any;
  data: any = {};
  response: any;

  constructor(private http: HttpClient) {}

  update() {
    this.http.put(`http://localhost:9090/api/medications/${this.code}`, this.data)
      .subscribe(res => this.response = res);
  }
}