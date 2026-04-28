import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-affiliation-post',
  standalone: true,
  imports: [FormsModule, JsonPipe, CommonModule],
  templateUrl: './affiliation-post.html',
  styleUrl: './affiliation-post.css',
})
export class AffiliationPost {
  physicianId: string = '';
  departmentId: string = '';
  primaryAffiliation: boolean = false;
  result: any = null;

  errors: any = {};
  backendError = '';
  success = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  createAffiliation() {
    this.zone.run(() => {
      this.errors = {};
      this.backendError = '';
      this.success = '';
      this.result = null;
      this.cd.detectChanges();
    });

    if (!this.physicianId) {
      this.errors.physicianId = 'Employee ID is required';
    }

    if (!this.departmentId) {
      this.errors.departmentId = 'Department ID is required';
    }

    if (this.errors.physicianId || this.errors.departmentId) {
      this.cd.detectChanges();

      setTimeout(() => {
        alert('Please fill required fields');
      }, 500);

      return;
    }

    const body = {
      departmentId: Number(this.departmentId),
      primaryAffiliation: this.primaryAffiliation
    };

    this.http.post(
      `http://localhost:9090/api/affiliations/physician/${Number(this.physicianId)}`,
      body
    ).subscribe({
      next: (res: any) => {
        this.zone.run(() => {
          this.result = res;
          this.success = 'Affiliation created successfully';
          this.backendError = '';
          this.errors = {};

          this.physicianId = '';
          this.departmentId = '';
          this.primaryAffiliation = false;

          this.cd.detectChanges();
        });

        setTimeout(() => {
          alert('✅ Affiliation created successfully');
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
            message = 'Error while creating affiliation';
          }

          this.backendError = message;
          this.success = '';
          this.result = null;

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