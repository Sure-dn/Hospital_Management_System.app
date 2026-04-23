import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-blocks-get',
  standalone: true,
  imports: [JsonPipe],
  templateUrl: './blocks-get.html'
})
export class BlocksGetComponent {

  data: any;

  constructor(private http: HttpClient) {}

  getBlocks() {
    this.http.get('http://localhost:9090/api/blocks')
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}