import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-affiliation-getbydepartment',
  imports: [FormsModule,JsonPipe,CommonModule],
  templateUrl: './affiliation-getbydepartment.html',
  styleUrl: './affiliation-getbydepartment.css',
})
export class AffiliationGetbydepartment {
  departmentId: string = '';
  result: any;

  constructor(private http: HttpClient) {}

  fetchPhysicians() {
    if (!this.departmentId) {
      alert('Please enter Department ID');
      return;
    }

    this.http.get( `http://localhost:9090/api/affiliations/department/${this.departmentId}`)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Error while fetching physicians');
          console.error(err);
        }
      });
  }
}
