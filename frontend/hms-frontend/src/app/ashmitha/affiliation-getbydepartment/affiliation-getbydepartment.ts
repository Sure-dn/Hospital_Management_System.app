import { Component, ChangeDetectorRef, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-affiliation-getbydepartment',
  imports: [FormsModule, JsonPipe, CommonModule],
  templateUrl: './affiliation-getbydepartment.html',
  styleUrl: './affiliation-getbydepartment.css',
})
export class AffiliationGetbydepartment {

  departmentId: string = '';
  result: any = null;
  error = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef,
    private zone: NgZone
  ) {}

  fetchPhysicians() {

    // reset
    this.error = '';
    this.result = null;

    // validation
    if (!this.departmentId) {
      this.error = 'Department ID is required';

      setTimeout(() => {
        alert(this.error);
      }, 300);

      return;
    }

    this.http.get(`http://localhost:9090/api/affiliations/department/${this.departmentId}`)
      .subscribe({
        next: (res: any) => {

          // 🔥 IMPORTANT FIX
          this.zone.run(() => {
            this.result = res;
            this.cd.detectChanges();
          });

        },

        error: (err) => {
          console.error(err);

          this.zone.run(() => {
            this.error = 'Error while fetching physicians';
            this.result = null;
            this.cd.detectChanges();
          });

          setTimeout(() => {
            alert(this.error);
          }, 300);
        }
      });
  }
}