import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-id',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './appointment-getbyid.html',
  styleUrl: './appointment-getbyid.css'
})
export class AppointmentGetByIdComponent {

  appointmentId: any;
  data: any = null;
  error: string = '';
  hasSearched = false;
  loading = false;

  private currentRequestId: number | null = null;

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  getById() {
    this.error = '';
    this.data = null;
    this.hasSearched = true;
    this.loading = true;

    const id = Number(this.appointmentId);

    if (!this.appointmentId || isNaN(id) || id <= 0) {
      this.error = 'Please enter a valid Appointment ID';
      this.loading = false;
      this.hasSearched = false;
      this.cd.detectChanges();
      return;
    }

    this.currentRequestId = id;

    const token = localStorage.getItem('token');

    this.http.get<any>(
      `http://localhost:9090/api/appointments/${id}`,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({
      next: (res) => {
        if (this.currentRequestId === id) {
          console.log('FULL RESPONSE:', res);

          this.data = res.data ? res.data : res;
          this.error = '';
          this.loading = false;

          this.cd.detectChanges();
        }
      },
      error: (err) => {
        if (this.currentRequestId === id) {
          console.error('ERROR:', err);

          this.error = err.error?.message || 'Appointment not found ❌';
          this.data = null;
          this.loading = false;

          this.cd.detectChanges();
        }
      }
    });
  }

  onIdChange() {
    if (this.hasSearched) {
      this.data = null;
      this.error = '';
      this.hasSearched = false;
      this.loading = false;
      this.cd.detectChanges();
    }
  }

  clear() {
    this.appointmentId = '';
    this.data = null;
    this.error = '';
    this.hasSearched = false;
    this.loading = false;
    this.currentRequestId = null;
    localStorage.removeItem('appointmentId');
  }
}