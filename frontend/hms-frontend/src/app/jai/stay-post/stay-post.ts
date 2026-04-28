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

  const token = localStorage.getItem('token');

  const body = {
  stayId: Number(this.stay.stayId),   // ✅ ADD THIS
  patientId: Number(this.stay.patientId),
  roomId: Number(this.stay.roomId),
  stayStart: new Date(this.stay.stayStart).toISOString(),
  stayEnd: new Date(this.stay.stayEnd).toISOString()
};

console.log(body);

  this.http.post('http://localhost:9090/api/stays', body, {
    headers: {
      Authorization: 'Bearer ' + token
    }
  })
  .subscribe({
    next: () => {
      alert('Stay added successfully ✅');
      this.message = 'Stay added successfully ✅';
      this.clearForm();
    },
    error: (err) => {
      console.error("BACKEND ERROR:", err); // 🔥 important
      this.error = err.error?.message || 'Error while saving ❌';
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