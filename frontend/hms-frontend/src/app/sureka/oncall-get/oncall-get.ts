import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-oncall-get',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './oncall-get.html'
})
export class OnCallGetComponent {

  employeeId!: number;
  data: any;

  constructor(private http: HttpClient) {}

  getByNurse() {
    this.http.get(`http://localhost:9090/api/nurses/${this.employeeId}/on-call`)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}
