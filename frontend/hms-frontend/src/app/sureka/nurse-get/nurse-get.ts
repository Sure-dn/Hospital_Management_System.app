import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-get-all-nurses',
  standalone: true,
  imports: [FormsModule,JsonPipe],
  templateUrl: './nurse-get.html'
})
export class GetAllNursesComponent {

  data: any;

  constructor(private http: HttpClient) {}

  getAll() {
    this.http.get('http://localhost:9090/api/nurses')
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}
