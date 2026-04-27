import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-patient-post',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './patient-post.html',
  styleUrl: './patient-post.css'
})
export class PatientPostComponent {

  patient = {
    ssn: '',
    name: '',
    address: '',
    phone: '',
    insuranceId: '',
    physicianId: ''
  };

  errors: any = {};
  success = '';
  backendError = '';

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  submit() {
    this.errors = {};
    this.success = '';
    this.backendError = '';

    const payload = {
      ssn: Number(this.patient.ssn),
      name: this.patient.name,
      address: this.patient.address,
      phone: this.patient.phone,
      insuranceId: Number(this.patient.insuranceId),
      pcp: Number(this.patient.physicianId)
    };

    this.http.post('http://localhost:9090/api/patients', payload)
      .subscribe({
        next: (res: any) => {
          this.success = '✅ Patient Added Successfully';

          this.patient = {
            ssn: '',
            name: '',
            address: '',
            phone: '',
            insuranceId: '',
            physicianId: ''
          };

          setTimeout(() => alert(this.success), 0);
        },

        error: (err) => {
          console.log('ERROR:', err);

          // ✅ VALIDATION ERRORS (object with fields)
          if (err.status === 400 && typeof err.error === 'object') {
            this.errors = err.error;
          }
          // ✅ OTHER ERRORS
          else if (err.error?.message) {
            this.backendError = err.error.message;
          }
          // else {
          //   this.backendError = 'Something went wrong';
          // }

          setTimeout(() => {
            alert(this.backendError || 'Validation error occurred');
          }, 0);
        }
      });
  }
}