import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-patient',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf, JsonPipe],
  templateUrl: './appointment-getbyssn.html',
  styleUrl: './appointment-getbyssn.css'
})
export class AppointmentGetByPatientComponent {

  ssn: any;
  appointments: any[] = [];
  error: string = '';
  hasSearched = false;
  loading = false;

  private currentRequestSsn: number | null = null;

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  getByPatient() {
    this.error = '';
    this.appointments = [];
    this.hasSearched = true;
    this.loading = true;

    const patientSsn = Number(this.ssn);

    if (!this.ssn || isNaN(patientSsn) || patientSsn <= 0) {
      this.error = 'Please enter a valid Patient SSN';
      this.loading = false;
      this.hasSearched = false;
      this.cd.detectChanges();
      return;
    }

    this.currentRequestSsn = patientSsn;

    const token = localStorage.getItem('token');

    this.http.get<any>(
      `http://localhost:9090/api/patients/${patientSsn}/appointments`,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({
      next: (res: any) => {
        if (this.currentRequestSsn === patientSsn) {
          console.log('FULL RESPONSE:', res);

          this.appointments = res.data ? res.data : res;
          this.loading = false;

          if (this.appointments.length === 0) {
            this.error = 'No appointments found for this patient';
          } else {
            this.error = '';
          }

          this.cd.detectChanges();
        }
      },

      error: (err) => {
        if (this.currentRequestSsn === patientSsn) {
          console.error(err);

          this.error = err.error?.message || 'Failed to load appointments ❌';
          this.appointments = [];
          this.loading = false;

          this.cd.detectChanges();
        }
      }
    });
  }

  onSsnChange() {
    if (this.hasSearched) {
      this.appointments = [];
      this.error = '';
      this.hasSearched = false;
      this.loading = false;
      this.cd.detectChanges();
    }
  }

  clear() {
    this.ssn = '';
    this.appointments = [];
    this.error = '';
    this.hasSearched = false;
    this.loading = false;
    this.currentRequestSsn = null;
  }
}