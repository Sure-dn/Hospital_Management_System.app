import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-department-post',
  imports: [FormsModule],
  templateUrl: './department-post.html',
  styleUrl: './department-post.css',
})
export class DepartmentPost {
  department = {
    departmentId: '',
    name: '',
    headEmployeeId: ''
  };

  constructor(private http: HttpClient) {}

  submit() {
    this.http.post('http://localhost:9090/api/departments', this.department)
      .subscribe({
        next: (res) => {
          alert('Department added successfully');
          console.log(res);
        },
        error: (err) => {
          alert('Error while saving department');
          console.error(err);
        }
      });
  }
}
