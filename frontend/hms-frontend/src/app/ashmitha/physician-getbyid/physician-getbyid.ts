import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-physician-getbyid',
  imports: [JsonPipe, FormsModule, NgIf],
  templateUrl: './physician-getbyid.html',
  styleUrl: './physician-getbyid.css',
})
export class PhysicianGetbyid {
  employeeId: string = '';
  result: any = null;
  error = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  fetchPhysician() {
    this.zone.run(() => {
      this.error = '';
      this.result = null;
      this.cd.detectChanges();
    });

    if (!this.employeeId) {
      this.zone.run(() => {
        this.error = 'Employee ID is required';
        this.cd.detectChanges();
      });

      setTimeout(() => {
        alert('Employee ID is required');
      }, 0);

      return;
    }

    this.http.get(`http://localhost:9090/api/physicians/${this.employeeId}`)
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
            else {
              this.error = 'Physician not found';
            }

            this.result = null;
            this.cd.detectChanges();
          });

          setTimeout(() => {
            alert(this.error || 'Physician not found');
          }, 0);
        }
      });
  }
}