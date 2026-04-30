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

  stayId: any;
  patientId: any;
  roomId: any;

  startDate: any;
  endDate: any;

  constructor(private http: HttpClient) {}

  submit() {

    // ✅ VALIDATION FIRST
    if (!this.stayId || !this.patientId || !this.roomId) {
      alert("Please fill all required fields");
      return;
    }

    if (!this.startDate || !this.endDate) {
      alert("Please select both dates");
      return;
    }

    const start = new Date(this.startDate);
    const end = new Date(this.endDate);

    // 🔥 CRITICAL FIX
    if (isNaN(start.getTime()) || isNaN(end.getTime())) {
      alert("Invalid date format");
      return;
    }

    const body = {
      stayId: this.stayId,
      patientId: this.patientId,
      roomId: this.roomId,
      startDate: start.toISOString(),
      endDate: end.toISOString()
    };

    const token = localStorage.getItem('token');

    this.http.post(
      `http://localhost:9090/api/stays`,
      body,
      {
        headers: {
          Authorization: 'Bearer ' + token
        }
      }
    ).subscribe({
      next: (res) => {
        console.log("SUCCESS:", res);
        alert("Stay added successfully ✅");
      },

      error: (err) => {
        console.error("ERROR:", err);

        const msg =
          err?.error?.message ||
          err?.error ||
          err?.message ||
          "Something went wrong";

        alert(msg); // 🔥 NOW THIS WILL TRIGGER
      }
    });
  }
}