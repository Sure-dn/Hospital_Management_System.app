import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-department-post',
  imports: [FormsModule, NgIf],
  templateUrl: './department-post.html',
  styleUrl: './department-post.css',
})
export class DepartmentPost {
  department = {
    departmentId: '',
    name: '',
    headEmployeeId: ''
  };

  errors: any = {};
  backendError = '';
  success = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  submit() {
    this.zone.run(() => {
      this.errors = {};
      this.backendError = '';
      this.success = '';
      this.cd.detectChanges();
    });

    this.http.post('http://localhost:9090/api/departments', this.department)
      .subscribe({
        next: (res: any) => {
          this.zone.run(() => {
            this.success = 'Department added successfully';
            this.backendError = '';
            this.errors = {};

            this.department = {
              departmentId: '',
              name: '',
              headEmployeeId: ''
            };

            this.cd.detectChanges();
          });

          setTimeout(() => {
            alert('✅ Department added successfully');
          }, 500);
        },

        error: (err) => {
          this.zone.run(() => {
            console.log('ERROR RESPONSE:', err);

            let message = '';

            if (typeof err.error === 'string') {
              message = err.error;
            }
            else if (err.error?.message) {
              message = err.error.message;
            }
            else if (err.error?.error) {
              message = err.error.error;
            }
            else if (err.error) {
              this.errors = { ...err.error };
            }
            else {
              message = 'Error while saving department';
            }

            this.backendError = message;
            this.success = '';

            this.cd.detectChanges();
          });

          setTimeout(() => {
            alert(this.backendError || 'Validation error occurred');
          }, 500);
        }
      });
  }
}