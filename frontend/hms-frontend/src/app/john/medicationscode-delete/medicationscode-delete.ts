import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-delete-medication',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './medicationscode-delete.html'
})
export class DeleteMedicationComponent {

  code: any;
  message: string = '';

  constructor(private http: HttpClient) {}
delete() {
  this.http.delete(`http://localhost:9090/api/medications/${this.code}`)
    .subscribe({
      next: () => {
        this.message = 'Deleted successfully';
      },
      error: (err) => {
        console.error(err);
        this.message = 'Delete failed';
      }
    });
}
}