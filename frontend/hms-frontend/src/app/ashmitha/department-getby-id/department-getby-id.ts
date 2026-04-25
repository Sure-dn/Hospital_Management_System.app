import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-department-getby-id',
  imports: [FormsModule,JsonPipe,CommonModule],
  templateUrl: './department-getby-id.html',
  styleUrl: './department-getby-id.css',
})
export class DepartmentGetbyID {
   departmentId: string = '';
  result: any;

  constructor(private http: HttpClient) {}

  fetchDepartment() {
    if (!this.departmentId) {
      alert('Please enter Department ID');
      return;
    }

    this.http.get(`http://localhost:9090/api/departments/${this.departmentId}`)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Department not found');
          console.error(err);
        }
      });
  }
}
