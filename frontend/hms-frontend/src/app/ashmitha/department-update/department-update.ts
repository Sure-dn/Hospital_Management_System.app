import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-department-update',
  imports: [FormsModule],
  templateUrl: './department-update.html',
  styleUrl: './department-update.css',
})
export class DepartmentUpdate {
  department = {
    departmentId: '',
    name: '',
    headEmployeeId: ''
  };

  constructor(private http: HttpClient) {}

  updateDepartment() {
    if (!this.department.departmentId) {
      alert('Please enter Department ID');
      return;
    }

    this.http.put(
      `http://localhost:9090/api/departments/${this.department.departmentId}`,
      this.department
    ).subscribe({
      next: (res) => {
        alert('Department updated successfully');
        console.log(res);
      },
      error: (err) => {
        alert('Error while updating department');
        console.error(err);
      }
    });
  }
}
