import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-oncall-delete',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './oncall-delete.html'
})
export class OnCallDeleteComponent {

  employeeId!: number;
  data: any;

  constructor(private http: HttpClient) {}

  delete() {
    this.http.delete(`http://localhost:9090/api/nurses/${this.employeeId}/on-call`)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}