import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule, NgIf } from '@angular/common';

@Component({
  selector: 'app-blocks-get',
  standalone: true,
  imports: [CommonModule,NgIf],
  templateUrl: './blocks-get.html',
  styleUrls: ['./blocks-get.css']
})
export class BlocksGetComponent {

  blocks: any[] = [];
  errorMsg: string = '';

  constructor(private http: HttpClient) {}

  getBlocks() {

    this.errorMsg = '';
    this.blocks = [];

    this.http.get('http://localhost:9090/api/blocks')
      .subscribe({

        // ✅ SUCCESS
        next: (res: any) => {
          console.log("API RESPONSE 👉", res);

          this.blocks = res.data;

          if (!this.blocks || this.blocks.length === 0) {
            this.errorMsg = "No blocks found";
          }
        },

        // ❌ ERROR
        error: (err) => {
          let msg = err.error?.message || "Failed to fetch blocks";
          this.errorMsg = msg;
          alert("❌ " + msg);
        }
      });
  }
}