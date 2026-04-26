import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-physician-delete',
  imports: [FormsModule],
  templateUrl: './physician-delete.html',
  styleUrl: './physician-delete.css',
})
export class PhysicianDelete {
   employeeId: string = '';

  constructor(private http: HttpClient) {}

  deletePhysician() {

    if (!this.employeeId) {
      alert('Enter Employee ID');
      return;
    }

    this.http.delete(
      `http://localhost:9090/api/physicians/${this.employeeId}`
    ).subscribe({
      next: () => {
        alert('✅ Deleted Successfully');
      },
      error: (err) => {
        alert('❌ Delete Failed');
        console.error(err);
      }
    });
  }
}
