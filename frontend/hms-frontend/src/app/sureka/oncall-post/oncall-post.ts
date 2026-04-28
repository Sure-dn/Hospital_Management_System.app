import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-oncall-post',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './oncall-post.html',
  styleUrls: ['./oncall-post.css']
})
export class OnCallPostComponent {

  employeeId: any = '';

  oncall = {
    blockFloor: '',
    blockCode: '',
    onCallStart: '',
    onCallEnd: ''
  };

  errors: any = {};
  success = '';
  backendError = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  assign() {

    this.errors = {};
    this.success = '';
    this.backendError = '';

    let hasError = false;

    // ✅ FRONTEND VALIDATION (LIKE NURSE)

    if (!this.employeeId) {
      this.errors.employeeId = "Employee ID is required";
      hasError = true;
    }

    if (!this.oncall.blockFloor) {
      this.errors.blockFloor = "Block Floor is required";
      hasError = true;
    }

    if (!this.oncall.blockCode) {
      this.errors.blockCode = "Block Code is required";
      hasError = true;
    }

    if (!this.oncall.onCallStart) {
      this.errors.onCallStart = "On-Call Start is required";
      hasError = true;
    }

    if (!this.oncall.onCallEnd) {
      this.errors.onCallEnd = "On-Call End is required";
      hasError = true;
    }

    // ❌ STOP API IF VALIDATION FAILS
    if (hasError) return;

    // ✅ CALL BACKEND
    this.http.post<any>(
      `http://localhost:9090/api/nurses/${this.employeeId}/on-call`,
      this.oncall
    )
    .subscribe({

      // ✅ SUCCESS
      next: (res: any) => {

        this.success = res.message || 'On-Call Assigned Successfully';

        this.employeeId = '';
        this.oncall = {
          blockFloor: '',
          blockCode: '',
          onCallStart: '',
          onCallEnd: ''
        };

        this.cd.detectChanges();

        setTimeout(() => {
          alert("✅ " + this.success);
        }, 0);
      },

      // ❌ ERROR
      error: (err) => {

        console.log("FULL ERROR 👉", err);

        let msg = '';

        // 🔥 PRIORITY 1: BACKEND EXCEPTION
        if (err.error?.message) {
          msg = err.error.message;
          this.backendError = msg;
        }

        // 🔥 PRIORITY 2: FIELD VALIDATION (DTO)
        else if (err.status === 400 && typeof err.error === 'object') {
          this.errors = { ...err.error };
          return;
        }

        // 🔥 PRIORITY 3: STRING ERROR
        else if (typeof err.error === 'string') {
          msg = err.error;
          this.backendError = msg;
        }

        // 🔥 FALLBACK
        else {
          msg = "Error while assigning on-call";
          this.backendError = msg;
        }

        this.cd.detectChanges();

        // ✅ POPUP ALERT
        if (msg) {
          setTimeout(() => {
            alert("❌ " + msg);
          }, 0);
        }
      }
    });
  }
}