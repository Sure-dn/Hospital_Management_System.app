import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-physician',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './appointment-getbyphysicianid.html',
  styleUrl: './appointment-getbyphysicianid.css'
})
export class AppointmentGetByPhysicianComponent {

  employeeId: any;
  appointments: any[] = [];
  error: string = '';
  hasSearched = false;
  loading = false;

  private currentRequestId: number | null = null;

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  getByPhysician() {

    this.error = '';
    this.appointments = [];
    this.hasSearched = true;
    this.loading = true;

    const empId = Number(this.employeeId);

    // Validation
    if (!this.employeeId || isNaN(empId) || empId <= 0) {
      this.error = 'Please enter a valid Physician ID';
      this.loading = false;
      this.hasSearched = false;
      this.cd.detectChanges();
      return;
    }

    this.currentRequestId = empId;

    const token = localStorage.getItem('token');

    this.http.get<any>(
      `http://localhost:9090/api/physicians/${empId}/appointments`,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({

      next: (res) => {
        if (this.currentRequestId === empId) {

          console.log('FULL RESPONSE:', res);

          this.appointments = res.data ? res.data : res;
          this.loading = false;

          if (this.appointments.length === 0) {
            this.error = 'No appointments found for this physician';
          } else {
            this.error = '';
          }

          this.cd.detectChanges();
        }
      },

      error: (err) => {
        if (this.currentRequestId === empId) {

          console.error(err);

          this.error = err.error?.message || 'Failed to load appointments ❌';
          this.appointments = [];
          this.loading = false;

          this.cd.detectChanges();
        }
      }
    });
  }

  // Clear when typing again
  onIdChange() {
    if (this.hasSearched) {
      this.appointments = [];
      this.error = '';
      this.hasSearched = false;
      this.loading = false;
      this.cd.detectChanges();
    }
  }

  clear() {
    this.employeeId = '';
    this.appointments = [];
    this.error = '';
    this.hasSearched = false;
    this.loading = false;
    this.currentRequestId = null;
  }
}