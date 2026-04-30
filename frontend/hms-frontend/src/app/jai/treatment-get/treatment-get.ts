import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgIf, NgFor, DatePipe } from '@angular/common';

@Component({
  selector: 'app-treatment-get',
  standalone: true,
  imports: [NgIf, NgFor, DatePipe],
  templateUrl: './treatment-get.html',
  styleUrl: './treatment-get.css'
})
export class TreatmentGetComponent {

  treatments: any[] = [];
  error = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef   // ✅ ADD THIS
  ) {}

  load() {
    this.error = '';
    this.treatments = [];

    this.http.get<any>('http://localhost:9090/api/treatments')
      .subscribe({

        next: (res) => {
          console.log("DATA:", res);

          this.treatments = res?.data || [];

          console.log("FINAL ARRAY:", this.treatments);

          if (this.treatments.length === 0) {
            alert("No treatments found");
          }

          this.cd.detectChanges(); // 🔥 VERY IMPORTANT
        },

        error: (err) => {
          console.error(err);

          const msg =
            err?.error?.message ||
            err?.error ||
            err?.message ||
            'Failed to fetch treatments ❌';

          this.error = msg;

          alert(msg);

          this.cd.detectChanges(); // 🔥 ALSO HERE
        }
      });
  }
}