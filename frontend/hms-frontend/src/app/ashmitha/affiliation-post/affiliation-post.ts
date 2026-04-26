import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-affiliation-post',
  standalone:true,
  imports: [FormsModule,JsonPipe,CommonModule],
  templateUrl: './affiliation-post.html',
  styleUrl: './affiliation-post.css',
})
export class AffiliationPost {
   physicianId: string = '';
  departmentId: string = '';
  primaryAffiliation: boolean = false;
  result: any;

  constructor(private http: HttpClient) {}

  createAffiliation() {
    if (!this.physicianId || !this.departmentId) {
      alert('Please enter Employee ID and Department ID');
      return;
    }

    const body = {
      departmentId: Number(this.departmentId),
      primaryAffiliation: this.primaryAffiliation
    };

    this.http.post( `http://localhost:9090/api/affiliations/physician/${Number(this.physicianId)}`, 
       body)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('Error while creating affiliation');
          console.error(err);
        }
      });
  }
}
