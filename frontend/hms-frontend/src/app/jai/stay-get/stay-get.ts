import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-stay-get',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './stay-get.html'
})
export class StayGetComponent {

  id: any;
  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get(`http://localhost:9090/api/stays/${this.id}`)
      .subscribe(res => this.data = res);
  }
}