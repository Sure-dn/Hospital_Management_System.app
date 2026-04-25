import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-physician-get',
  imports: [JsonPipe],
  templateUrl: './physician-get.html',
  styleUrl: './physician-get.css',
})
export class PhysicianGet {
   data: any;

  constructor(private http: HttpClient) {}

  load() {
    this.http.get('http://localhost:9090/api/physicians')
      .subscribe(res => this.data = res);
  }
}
