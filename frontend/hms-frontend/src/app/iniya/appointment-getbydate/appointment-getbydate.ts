import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-date',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './appointment-getbydate.html',
  styleUrl: './appointment-getbydate.css'
})
export class AppointmentGetByDateComponent {

  date = '';
  appointments: any[] = [];
  error = '';
  loading = false;
  hasSearched = false;

  private currentRequestDate: string | null = null;

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  getByDate() {
    this.error = '';
    this.appointments = [];
    this.loading = true;
    this.hasSearched = true;

    if (!this.date) {
      this.error = 'Please select a date';
      this.loading = false;
      this.hasSearched = false;
      this.cd.detectChanges();
      return;
    }

    this.currentRequestDate = this.date;

    const token = localStorage.getItem('token');

    this.http.get<any>(
      `http://localhost:9090/api/appointments/by-date?date=${this.date}`,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({
      next: (res: any) => {
        if (this.currentRequestDate === this.date) {
          console.log('FULL RESPONSE:', res);

          this.appointments = res.data ? res.data : res;
          this.loading = false;

          if (this.appointments.length === 0) {
            this.error = 'No appointments found for this date';
          }

          this.cd.detectChanges();
        }
      },
      error: (err) => {
        if (this.currentRequestDate === this.date) {
          console.error(err);

          this.error = err.error?.message || 'Failed to load appointments ❌';
          this.appointments = [];
          this.loading = false;

          this.cd.detectChanges();
        }
      }
    });
  }

  onDateChange() {
    if (this.hasSearched) {
      this.appointments = [];
      this.error = '';
      this.loading = false;
      this.hasSearched = false;
      this.cd.detectChanges();
    }
  }

  clear() {
    this.date = '';
    this.appointments = [];
    this.error = '';
    this.loading = false;
    this.hasSearched = false;
    this.currentRequestDate = null;
  }
}