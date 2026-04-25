import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-physician-update',
  imports: [FormsModule],
  templateUrl: './physician-update.html',
  styleUrl: './physician-update.css',
})
export class PhysicianUpdate {

  physician = {
    employeeId: '',
    name: '',
    position: '',
    ssn: ''
  };

  constructor(private http: HttpClient) {}

  updatePhysician() {

    if (!this.physician.employeeId) {
      alert('Enter Employee ID');
      return;
    }

    this.http.put(
      `http://localhost:9090/api/physicians/${this.physician.employeeId}`,
      this.physician
    ).subscribe({
      next: () => {
        alert('✅ Updated Successfully');
      },
      error: (err) => {
        alert('❌ Update Failed');
        console.error(err);
      }
    });
  }
}
