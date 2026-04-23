import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-get-nurse-by-id',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './employeeid-get.html'
})
export class GetNurseByIdComponent {

  id!: number;
  data: any;

  constructor(private http: HttpClient) {}

  getById() {
    this.http.get(`http://localhost:9090/api/nurses/${this.id}`)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}
