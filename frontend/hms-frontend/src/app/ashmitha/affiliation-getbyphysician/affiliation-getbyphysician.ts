import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-affiliation-getbyphysician',
  imports: [FormsModule, JsonPipe, CommonModule],
  templateUrl: './affiliation-getbyphysician.html',
  styleUrl: './affiliation-getbyphysician.css',
})
export class AffiliationGetbyphysician {
  employeeId: string = '';
  result: any = null;
  error = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  fetchAffiliations() {
    this.zone.run(() => {
      this.error = '';
      this.result = null;
      this.cd.detectChanges();
    });

    if (!this.employeeId) {
      this.zone.run(() => {
        this.error = 'Employee ID is required';
        this.cd.detectChanges();

        setTimeout(() => {
          this.cd.detectChanges();
        }, 0);
      });

      setTimeout(() => {
        alert('Employee ID is required');
      }, 500);

      return;
    }

    this.http.get(`http://localhost:9090/api/affiliations/physician/${this.employeeId}`)
      .subscribe({
        next: (res: any) => {
          this.zone.run(() => {
            this.result = res;
            this.error = '';
            this.cd.detectChanges();

            setTimeout(() => {
              this.cd.detectChanges();
            }, 0);
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
              this.error = 'Physician not found';
            }

            this.result = null;
            this.cd.detectChanges();

            setTimeout(() => {
              this.cd.detectChanges();
            }, 0);
          });

          setTimeout(() => {
            alert(this.error || 'Physician not found');
          }, 500);
        }
      });
  }
}