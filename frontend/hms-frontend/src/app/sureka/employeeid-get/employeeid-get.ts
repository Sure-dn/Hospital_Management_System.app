import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-nurse-by-id',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './employeeid-get.html',
  styleUrls: ['./employeeid-get.css']
})
export class GetNurseByIdComponent {

  id!: number;
  data: any = null;
  errorMsg = '';
  loading = false;
  hasSearched = false;

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  getById() {

    this.errorMsg = '';
    this.data = null;
    this.loading = true;
    this.hasSearched = true;

    // ✅ Validation
    if (!this.id) {
      this.loading = false;
      this.errorMsg = "Employee ID is required";
      alert("❌ " + this.errorMsg);
      this.cd.detectChanges();
      return;
    }

    this.http.get<any>(`http://localhost:9090/api/nurses/${this.id}`)
      .subscribe({

        // ✅ SUCCESS
        next: (res) => {

          console.log("API RESPONSE 👉", res);

          // 🔥 Handle wrapped response
          this.data = res?.data || res;

          this.loading = false;

          // 🔴 No data case
          if (!this.data) {
            this.errorMsg = "No nurse found with this ID";
            alert("❌ " + this.errorMsg);
          }

          this.cd.detectChanges();
        },

        // ❌ ERROR
        error: (err) => {

          console.log("FULL ERROR 👉", err);

          let msg = "Something went wrong";

          if (err.status === 404) {
            msg = "No nurse found with this ID";
          } 
          else if (err.error?.message) {
            msg = err.error.message;
          } 
          else if (typeof err.error === 'string') {
            msg = err.error;
          }

          this.errorMsg = msg;
          this.loading = false;
          this.data = null;

          alert("❌ " + msg);

          this.cd.detectChanges();
        }
      });
  }

  // 🔄 CLEAR ON INPUT CHANGE
  onIdChange() {
    if (this.hasSearched) {
      this.data = null;
      this.errorMsg = '';
      this.loading = false;
      this.hasSearched = false;

      this.cd.detectChanges();
    }
  }
}