import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-get-medication',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './medicationscode-get.html'
})
export class GetMedicationComponent {

  code: any;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:9090/api/medications/${this.code}`)
      .subscribe(res => this.data = res);
  }
}