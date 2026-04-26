import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-appointment-get-by-patient',
  standalone: true,
  imports: [FormsModule, NgFor, NgIf,JsonPipe],
  templateUrl: './appointment-getbyssn.html',
  styleUrl: './appointment-getbyssn.css'
})
export class AppointmentGetByPatientComponent {

  ssn = '';
  appointments: any[] = [];
  error = '';
  searched = false;

  constructor(private http: HttpClient) {}

  getHeaders() {
    return new HttpHeaders({
      'Authorization': 'Basic ' + btoa('username:123')
    });
  }

  getByPatient() {
    this.error = '';
    this.appointments = [];
    this.searched = true;

    if (!this.ssn) {
      alert('❌ Please enter Patient SSN');
      return;
    }

    this.http.get<any>(`http://localhost:9090/api/patients/${this.ssn}/appointments`, {
      headers: this.getHeaders()
    }).subscribe({
      next: (res: any) => {
        console.log('FULL RESPONSE:', res);

        this.appointments = res.data ? res.data : res;

        alert('✅ Patient appointments loaded');
      },
      error: (err) => {
        console.error(err);
        this.error = err.error?.message || '❌ Failed to load appointments';
        alert(this.error);
      }
    });
  }
}