import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-get-by-medication',
  standalone: true,
  imports: [FormsModule, JsonPipe, NgIf],
  templateUrl: './prescriptionscode-medication-get.html'
})
export class GetByMedicationComponent {

  code: any;
  data: any;
  errorMsg: string = '';

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  load() {
    this.errorMsg = '';
    this.http.get(`http://localhost:9090/api/prescriptions/medication/${this.code}`)
      .subscribe({
        next: (res) => { this.data = res; this.cdr.detectChanges(); },
        error: (err) => { this.errorMsg = (typeof err.error === 'string' && err.error.trim()) ? err.error.trim() : `Error ${err.status}: ${err.statusText || err.message}`; this.cdr.detectChanges(); }
      });
  }
}