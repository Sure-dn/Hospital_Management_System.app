import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-physician-update',
  imports: [FormsModule, NgIf],
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

  errors: any = {};
  backendError = '';
  success = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  updatePhysician() {
    this.errors = {};
    this.backendError = '';
    this.success = '';
    this.cd.detectChanges();

    if (!this.physician.employeeId) {
      this.errors = { employeeId: 'Employee ID is required' };
      this.cd.detectChanges();

      setTimeout(() => {
        alert('Employee ID is required');
      }, 500);

      return;
    }

    this.http.put(
      `http://localhost:9090/api/physicians/${this.physician.employeeId}`,
      this.physician
    ).subscribe({
      next: (res: any) => {
        this.success = res.message || 'Physician Updated Successfully';
        this.backendError = '';
        this.errors = {};

        this.cd.detectChanges();

        setTimeout(() => {
          alert('✅ Physician Updated Successfully');
        }, 500);
      },

      error: (err) => {
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
          message = 'Update Failed';
        }

        this.backendError = message;
        this.success = '';

        this.cd.detectChanges();

        setTimeout(() => {
          alert(this.backendError || 'Validation error occurred');
        }, 500);
      }
    });
  }
}