import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, JsonPipe } from '@angular/common';

@Component({
  selector: 'app-update-nurse',
  standalone: true,
  imports: [FormsModule, NgIf, JsonPipe], // ✅ added NgIf
  templateUrl: './employeeid-put.html',
  styleUrl:'./employeeid-put.css'
})
export class UpdateNurseComponent {

  id!: number;

  nurse = {
    name: '',
    position: '',
    registered: true,
    ssn: 0
  };

  data: any;

  constructor(private http: HttpClient) {}

  update() {
    console.log("ID 👉", this.id);
    console.log("BODY 👉", this.nurse);

    const token = localStorage.getItem('token');

    const headers = {
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json'
    };

    this.http.put(
      `http://localhost:9090/api/nurses/${this.id}`,
      {
        employeeId: this.id,   // 🔥 IMPORTANT FIX
        ...this.nurse
      },
      { headers }
    ).subscribe({
      next: res => {
        console.log("Response 👉", res);
        this.data = res;
        alert("✅ Nurse updated successfully!");
      },
      error: err => {
        console.error(err);
        alert(err.error?.message || "❌ Update failed!");
      }
    });
  }
}