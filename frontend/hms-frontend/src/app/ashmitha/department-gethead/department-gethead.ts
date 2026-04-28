import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-department-gethead',
  imports: [FormsModule, JsonPipe, NgIf],
  templateUrl: './department-gethead.html',
  styleUrl: './department-gethead.css',
})
export class DepartmentGethead {
  departmentId: string = '';
  result: any = null;

  error = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  fetchDepartmentHead() {
    this.zone.run(() => {
      this.error = '';
      this.result = null;
      this.cd.detectChanges();
    });

    if (!this.departmentId) {
      this.zone.run(() => {
        this.error = 'Department ID is required';
        this.cd.detectChanges();
      });

      setTimeout(() => {
        alert('Department ID is required');
      }, 500);

      return;
    }

    this.http.get(`http://localhost:9090/api/departments/${this.departmentId}/head`)
      .subscribe({
        next: (res: any) => {
          this.zone.run(() => {
            this.result = res;
            this.error = '';
            this.cd.detectChanges();
          });
        },

        error: (err) => {
          this.zone.run(() => {
            console.log('ERROR RESPONSE:', err);

            if (typeof err.error === 'string') {
              this.error = err.error;
            }
            else if (err.error?.message) {
              this.error = err.error.message;
            }
            else if (err.error?.error) {
              this.error = err.error.error;
            }
            else {
              this.error = 'Department not found';
            }

            this.result = null;

            this.cd.detectChanges();

            setTimeout(() => {
              this.cd.detectChanges();
            }, 0);
          });

          setTimeout(() => {
            alert(this.error || 'Department not found');
          }, 500);
        }
      });
  }
}