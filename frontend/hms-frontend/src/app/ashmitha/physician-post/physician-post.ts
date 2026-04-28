import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-physician-post',
  imports: [CommonModule, FormsModule],
  templateUrl: './physician-post.html',
  styleUrl: './physician-post.css',
})
export class PhysicianPost {

  physician = {
    employeeId: '',
    name: '',
    position: '',
    ssn: ''
  };

  errors: any = {};
  success = '';
  backendError = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  submit() {
    this.errors = {};
    this.success = '';
    this.backendError = '';
    this.cd.detectChanges();

    this.http.post('http://localhost:9090/api/physicians', this.physician)
      .subscribe({
        next: (res: any) => {
          this.success = res.message || 'Physician Added Successfully';

          this.physician = {
            employeeId: '',
            name: '',
            position: '',
            ssn: ''
          };

          this.cd.detectChanges();

          setTimeout(() => {
            alert('✅ Physician Added Successfully');
          }, 0);
        },

        error: (err) => {
          console.log('ERROR RESPONSE:', err);

          if (typeof err.error === 'string') {
            this.backendError = err.error;
          }
          else if (err.error?.message) {
            this.backendError = err.error.message;
          }
          else if (err.error) {
            this.errors = { ...err.error };
          }
          else {
            this.backendError = 'Error while saving physician';
          }

          this.cd.detectChanges();

          setTimeout(() => {
            alert(this.backendError || 'Validation error occurred');
          }, 0);
        }
      });
  }
}