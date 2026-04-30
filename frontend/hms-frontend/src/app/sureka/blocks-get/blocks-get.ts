import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-blocks-get',
  standalone: true,
  imports: [NgIf, NgFor], // ✅ ADD THIS
  templateUrl: './blocks-get.html',
  styleUrls: ['./blocks-get.css']
})
export class BlocksGetComponent {

  data: any = { data: [] };

  constructor(private http: HttpClient) {}

  getBlocks() {
    this.http.get('http://localhost:9090/api/blocks')
      .subscribe({
        next: (res: any) => {
          console.log("API 👉", res);
          this.data = res;
        },
        error: err => console.error(err)
      });
  }
}