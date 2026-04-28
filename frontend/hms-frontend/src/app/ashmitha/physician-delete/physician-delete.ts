import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-physician-delete',
  imports: [FormsModule, NgIf],
  templateUrl: './physician-delete.html',
  styleUrl: './physician-delete.css',
})
export class PhysicianDelete {
  employeeId: string = '';

  error = '';
  success = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  deletePhysician() {
    this.zone.run(() => {
      this.error = '';
      this.success = '';
      this.cd.detectChanges();
    });

    if (!this.employeeId) {
      this.zone.run(() => {
        this.error = 'Employee ID is required';
        this.cd.detectChanges();
      });

      setTimeout(() => alert('Employee ID is required'), 500);
      return;
    }

    this.http.delete(`http://localhost:9090/api/physicians/${this.employeeId}`)
      .subscribe({
        next: (res: any) => {
          this.zone.run(() => {
            this.success = res.message || 'Physician Deleted Successfully';
            this.error = '';
            this.employeeId = '';
            this.cd.detectChanges();
          });

          setTimeout(() => alert('✅ Physician Deleted Successfully'), 500);
        },

        error: (err) => {
          this.zone.run(() => {
            console.log('ERROR RESPONSE:', err);

            if (typeof err.error === 'string') {
              this.error = err.error;
            } else if (err.error?.message) {
              this.error = err.error.message;
            } else if (err.error?.error) {
              this.error = err.error.error;
            } else {
              this.error = 'Delete Failed';
            }

            this.success = '';
            this.cd.detectChanges();
          });

          setTimeout(() => alert(this.error), 500);
        }
      });
  }
}