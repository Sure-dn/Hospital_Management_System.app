import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-create-nurse',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './nurse-post.html',
  styleUrl: './nurse-post.css'
})
export class CreateNurseComponent {

  nurse = {
    employeeId: 0,
    name: '',
    position: '',
    registered: true,
    ssn: 0,
  };

  data: any = null;
  error = '';
  successMessage = '';
  hasSubmitted = false;
  loading = false;

  constructor(private http: HttpClient) {}

  create() {
    console.log("Sending Data 👉", this.nurse);

    this.error = '';
    this.successMessage = '';
    this.data = null;
    this.hasSubmitted = true;
    this.loading = true;

    this.http.post<any>('http://localhost:9090/api/nurses', this.nurse)
      .subscribe({
        next: (res) => {
          console.log("Response 👉", res);

          // ✅ SUCCESS ALERT
          alert("✅ Nurse added successfully to database!");

          this.data = res;
          this.successMessage = '✅ Nurse created successfully!';
          this.loading = false;

          // reset form
          this.nurse = {
            employeeId: 0,
            name: '',
            position: '',
            registered: true,
            ssn: 0,
          };
        },

        error: (err) => {
          console.error(err);

          // ❌ ERROR ALERT
          alert("❌ Failed to create nurse!");

          this.error = err?.error?.message || '❌ Failed to create nurse';
          this.loading = false;
        }
      });
  }
}