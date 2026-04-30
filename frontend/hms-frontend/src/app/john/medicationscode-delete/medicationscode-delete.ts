import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe, NgIf } from '@angular/common';

@Component({
  selector: 'app-delete-medication',
  standalone: true,
  imports: [FormsModule, NgIf, JsonPipe],
  templateUrl: './medicationscode-delete.html'
})
export class DeleteMedicationComponent {

  code: any;
  message: string = '';
  errorMsg: string = '';

  constructor(private http: HttpClient, private cdr: ChangeDetectorRef) {}

  delete() {
    this.errorMsg = '';
    this.message = '';
    this.http.delete(`http://localhost:9090/api/medications/${this.code}`)
      .subscribe({
        next: () => { this.message = 'Deleted successfully'; this.cdr.detectChanges(); },
        error: (err) => { this.errorMsg = (typeof err.error === 'string' && err.error.trim()) ? err.error.trim() : `Error ${err.status}: ${err.statusText || err.message}`; this.cdr.detectChanges(); }
      });
  }
}