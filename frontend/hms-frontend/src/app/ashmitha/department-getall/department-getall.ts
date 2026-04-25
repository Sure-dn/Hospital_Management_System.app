import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-department-getall',
  imports: [FormsModule,JsonPipe],
  templateUrl: './department-getall.html',
  styleUrl: './department-getall.css',
})
export class DepartmentGetall {
   result: any;

  constructor(private http: HttpClient) {}

  fetchDepartments() {
    this.http.get('http://localhost:9090/api/departments')
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Error while fetching departments');
          console.error(err);
        }
      });
  }
}
