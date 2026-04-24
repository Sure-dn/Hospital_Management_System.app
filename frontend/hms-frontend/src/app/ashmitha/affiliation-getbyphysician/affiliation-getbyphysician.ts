import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-affiliation-getbyphysician',
  imports: [FormsModule,JsonPipe,CommonModule],
  templateUrl: './affiliation-getbyphysician.html',
  styleUrl: './affiliation-getbyphysician.css',
})
export class AffiliationGetbyphysician {
  employeeId: string = '';
  result: any;

  constructor(private http: HttpClient) {}

  fetchAffiliations() {
    if (!this.employeeId) {
      alert('Please enter Employee ID');
      return;
    }

    this.http.get( `http://localhost:9090/api/affiliations/physician/${this.employeeId}`)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Error while fetching affiliations');
          console.error(err);
        }
      });
  }
}
