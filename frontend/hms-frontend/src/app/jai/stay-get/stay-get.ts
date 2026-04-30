import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf , DatePipe } from '@angular/common';

@Component({
  selector: 'app-stay-get',
  standalone: true,
  imports: [FormsModule, NgIf,DatePipe],
  templateUrl: './stay-get.html',
  styleUrl: './stay-get.css'
})
export class StayGetComponent {

  stayId: any;
  data: any = null;
  error: string = '';
  loading = false;
  hasSearched = false;

  constructor(
    private http: HttpClient,
    private cd: ChangeDetectorRef
  ) {}

  fetch() {

    // 🔴 1. EMPTY INPUT VALIDATION
    if (!this.stayId) {
      alert("Stay ID should not be empty");
      return;
    }

    this.error = '';
    this.data = null;
    this.loading = true;
    this.hasSearched = true;

    const token = localStorage.getItem('token');

    this.http.get<any>(
      `http://localhost:9090/api/stays/${this.stayId}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    ).subscribe({

      next: (res) => {
        console.log("RESPONSE:", res);

        // ✅ handle wrapped + direct
        this.data = res?.data || res;

        this.loading = false;

        // 🔴 IF NO DATA
        if (!this.data) {
          alert(`No stay found with ID ${this.stayId}`);
        }

        this.cd.detectChanges();
      },

      error: (err) => {
        console.error("ERROR:", err);

        let msg = "Something went wrong";

        if (err.status === 401) {
          msg = "Unauthorized! Please login again";
        } 
        else if (err.status === 404) {
          msg = `No stay found with ID ${this.stayId}`;
        } 
        else if (typeof err.error === 'string') {
          msg = err.error;
        } 
        else if (err.error?.message) {
          msg = err.error.message;
        } 
        else if (err.message) {
          msg = err.message;
        }

        alert(msg); // ✅ clean alert

        this.error = msg;
        this.loading = false;
        this.data = null;

        this.cd.detectChanges();
      }
    });
  }

  // 🔄 CLEAR WHEN INPUT CHANGES
  onIdChange() {
    if (this.hasSearched) {
      this.data = null;
      this.error = '';
      this.loading = false;
      this.hasSearched = false;

      this.cd.detectChanges();
    }
  }
}