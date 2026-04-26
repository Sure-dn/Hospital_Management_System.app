import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-physician-post',
  imports: [CommonModule, FormsModule,  RouterOutlet],
  templateUrl: './physician-post.html',
  styleUrl: './physician-post.css',
})
export class PhysicianPost {
   physician = {
    employeeId: '',
    name: '',
    position: '',
    ssn: ''
  };

  constructor(private http: HttpClient) {}

  submit() {
    this.http.post('http://localhost:9090/api/physicians', this.physician)
      .subscribe({
        next: (res) => {
          alert('✅ Physician Added Successfully');
          console.log(res);
        },
        error: (err) => {
          alert('❌ Error while saving physician');
          console.error(err);
        }
      });
  }

}
