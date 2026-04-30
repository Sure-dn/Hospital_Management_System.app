import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-get-by-date',
  standalone: true,
  imports: [FormsModule, JsonPipe, NgIf],
  templateUrl: './prescriptions-datefromto-get.html'
})
export class GetByDateComponent {

  from: any;
  to: any;
  data: any;
  errorMsg: string = '';

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  load() {
    this.errorMsg = '';
    this.http.get(`http://localhost:9090/api/prescriptions?from=${this.from}&to=${this.to}`)
      .subscribe({
        next: (res) => { this.data = res; this.cdr.detectChanges(); },
        error: (err) => { this.errorMsg = (typeof err.error === 'string' && err.error.trim()) ? err.error.trim() : `Error ${err.status}: ${err.statusText || err.message}`; this.cdr.detectChanges(); }
      });
  }
}