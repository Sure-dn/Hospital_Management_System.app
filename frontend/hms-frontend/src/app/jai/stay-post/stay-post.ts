import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-stay-post',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './stay-post.html',
  styleUrl: './stay-post.css'
})
export class StayPostComponent {

  stay = {
    stayId: '',
    patientId: '',
    roomId: '',
    stayStart: '',
    stayEnd: ''
  };

  message = '';
  error = '';

  constructor(private http: HttpClient) {}

  submit() {
    this.message = '';
    this.error = '';

    this.http.post('http://localhost:9090/api/stays', this.stay)
      .subscribe({
        next: () => {
          this.message = 'Stay added successfully ✅';
          this.clearForm();
        },
        error: (err) => {
          console.error(err);
          this.error = 'Error while saving ❌';
        }
      });
  }

  clearForm() {
    this.stay = {
      stayId: '',
      patientId: '',
      roomId: '',
      stayStart: '',
      stayEnd: ''
    };
  }
}