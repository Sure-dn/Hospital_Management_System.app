import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-department-gethead',
  imports: [FormsModule,JsonPipe],
  templateUrl: './department-gethead.html',
  styleUrl: './department-gethead.css',
})
export class DepartmentGethead {
   departmentId: string = '';
  result: any;

  constructor(private http: HttpClient) {}

  fetchDepartmentHead() {
    if (!this.departmentId) {
      alert('Please enter Department ID');
      return;
    }

    this.http.get(`http://localhost:9090/api/departments/${this.departmentId}/head`)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Error while fetching department head');
          console.error(err);
        }
      });
  }
}
