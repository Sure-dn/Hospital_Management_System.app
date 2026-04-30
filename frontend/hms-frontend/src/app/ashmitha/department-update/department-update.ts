import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-department-update',
  imports: [FormsModule, NgIf],
  templateUrl: './department-update.html',
  styleUrl: './department-update.css',
})
export class DepartmentUpdate {
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

  updateDepartment() {
    this.zone.run(() => {
      this.errors = {};
      this.backendError = '';
      this.success = '';
      this.cd.detectChanges();
    });

    if (!this.department.departmentId) {
      this.zone.run(() => {
        this.errors = { departmentId: 'Department ID is required' };
        this.cd.detectChanges();
      });

      setTimeout(() => {
        alert('Department ID is required');
      }, 500);

      return;
    }

    this.http.put(
      `http://localhost:9090/api/departments/${this.department.departmentId}`,
      this.department
    ).subscribe({
      next: (res: any) => {
        this.zone.run(() => {
          this.success = 'Department updated successfully';
          this.backendError = '';
          this.errors = {};
          this.cd.detectChanges();
        });

        setTimeout(() => {
          alert('✅ Department updated successfully');
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
            message = 'Error while updating department';
          }

          this.backendError = message;
          this.success = '';

          this.cd.detectChanges();

          setTimeout(() => {
            this.cd.detectChanges();
          }, 0);
        });

        setTimeout(() => {
          alert(this.backendError || 'Validation error occurred');
        }, 500);
      }
    });
  }
}