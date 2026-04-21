import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-physician-procedures',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './physician-procedures.html'
})
export class PhysicianProceduresComponent {

  id: any;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:9090/api/physicians/${this.id}/procedures`)
      .subscribe(res => this.data = res);
  }
}