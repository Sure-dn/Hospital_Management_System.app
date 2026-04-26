import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-procedures-get',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './procedures-get.html',
  styleUrl: './procedures-get.css'
})
export class ProceduresGetComponent {

  procedures: any[] = [];

  constructor(private http: HttpClient) {}

  load() {
    this.http.get<any>('http://localhost:9090/api/procedures')
      .subscribe(res => {
        this.procedures = res.data; // 🔥 IMPORTANT
      });
  }
}