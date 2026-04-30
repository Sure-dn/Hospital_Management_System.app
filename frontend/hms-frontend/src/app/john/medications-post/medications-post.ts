import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-create-medication',
  standalone: true,
  imports: [FormsModule, JsonPipe, NgIf],
  templateUrl: './medications-post.html'
})
export class CreateMedicationComponent {

  data: any = {};
  response: any;
  errorMsg: string = '';

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  create() {
    this.errorMsg = '';
    this.http.post('http://localhost:9090/api/medications', this.data)
      .subscribe({
        next: (res) => { this.response = res; this.cdr.detectChanges(); },
        error: (err) => { this.errorMsg = (typeof err.error === 'string' && err.error.trim()) ? err.error.trim() : `Error ${err.status}: ${err.statusText || err.message}`; this.cdr.detectChanges(); }
      });
  }
}