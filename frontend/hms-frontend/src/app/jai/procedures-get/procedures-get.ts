import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-procedures-get',
  standalone: true,
  imports: [JsonPipe],
  templateUrl: './procedures-get.html',
  styleUrls: ['./procedures-get.css']
})
export class ProceduresGetComponent {

  data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get('http://localhost:9090/api/procedures')
      .subscribe(res => this.data = res);
  }
}