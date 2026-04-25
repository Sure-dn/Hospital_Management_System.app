import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-physician-getbyid',
  imports: [JsonPipe,FormsModule],
  templateUrl: './physician-getbyid.html',
  styleUrl: './physician-getbyid.css',
})
export class PhysicianGetbyid {
  employeeId: string = '';
  result: any;

  constructor(private http: HttpClient) {}

  fetchPhysician() {
    this.http.get(`http://localhost:9090/api/physicians/${this.employeeId}`)
      .subscribe({
        next: (res) => {
          this.result = res;
        },
        error: (err) => {
          alert('❌ Physician not found');
          console.error(err);
        }
      });
  }

}
