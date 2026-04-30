import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-create-nurse',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './nurse-post.html',
  styleUrl: './nurse-post.css',
})
export class CreateNurseComponent {

  nurse: any = {
    employeeId: null,
    name: '',
    position: '',
    registered: false,
    ssn: null
  };

  errors: any = {};
  success = '';
  backendError = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  create() {

    // RESET
    this.errors = {};
    this.success = '';
    this.backendError = '';

    this.http.post('http://localhost:9090/api/nurses', this.nurse)
      .subscribe({

        // ✅ SUCCESS
        next: (res: any) => {

          this.success = res.message || 'Nurse Added Successfully';

          this.nurse = {
            employeeId: null,
            name: '',
            position: '',
            registered: false,
            ssn: null
          };

          this.cd.detectChanges();
        },

        // ❌ ERROR HANDLING
        error: (err) => {

          console.log("FULL ERROR 👉", err);

          let alertMessage = '';

          // ✅ Case 1: Validation errors (Map from backend)
          if (err.status === 400 && typeof err.error === 'object') {

            this.errors = err.error;

            alertMessage = Object.values(err.error).join('\n');
          }

          // ✅ Case 2: Custom backend exception
          else if (err.error?.message) {

            this.backendError = err.error.message;
            alertMessage = this.backendError;
          }

          // ✅ Case 3: Plain string error
          else if (typeof err.error === 'string') {

            this.backendError = err.error;
            alertMessage = this.backendError;
          }

          // ✅ Fallback
          else {

            this.backendError = "Something went wrong";
            alertMessage = this.backendError;
          }

          this.cd.detectChanges();

          // 🔥 SHOW ALERT (single place)
          setTimeout(() => alert(alertMessage), 0);
        }

      });
  }
}