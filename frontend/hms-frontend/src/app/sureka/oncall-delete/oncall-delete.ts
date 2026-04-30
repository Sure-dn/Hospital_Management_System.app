import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-oncall-delete',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './oncall-delete.html',
  styleUrls: ['./oncall-delete.css']
})
export class OnCallDeleteComponent {

  employeeId!: number;

  data: any = null;
  errorMsg = '';

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  delete() {

    // reset UI
    this.data = null;
    this.errorMsg = '';

    // ✅ validation
    if (!this.employeeId) {
      this.errorMsg = "Employee ID is required";
      return;
    }

    this.http.delete(`http://localhost:9090/api/nurses/${this.employeeId}/on-call`)
      .subscribe({

        // ✅ SUCCESS
        next: (res: any) => {

          console.log("SUCCESS 👉", res);

          this.data = res;

          // optional safety
          if (!this.data?.message) {
            this.data = { message: "Deleted successfully" };
          }

          this.cdr.detectChanges();
        },

        // ❌ ERROR
        error: (err) => {

          console.log("FULL ERROR 👉", err);

          let msg = '';

          if (err.error?.message) {
            msg = err.error.message;
          } 
          else if (typeof err.error === 'string') {
            msg = err.error;
          } 
          else {
            msg = "Delete failed";
          }

          this.errorMsg = msg;

          this.cdr.detectChanges();
        }
      });
  }
}