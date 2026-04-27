import { Component, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-stay-get',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './stay-get.html',
  styleUrl: './stay-get.css'
})
export class StayGetComponent {

  stayId: any;
  data: any = null;
  error: string = '';
  loading = false;
  hasSearched = false;

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  fetch() {
    this.error = '';
    this.data = null;
    this.loading = true;
    this.hasSearched = true;

    const token = localStorage.getItem('token');

    this.http.get(
      `http://localhost:9090/api/stays/${this.stayId}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    ).subscribe({
      next: (res: any) => {
        console.log("RESPONSE:", res);

        // handle both wrapped & direct response
        this.data = res.data ? res.data : res;

        this.loading = false;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.log(err);

        let message = "Something went wrong";

        if (typeof err.error === 'string') {
          message = err.error;
        } else if (err.error?.message) {
          message = err.error.message;
        }

        alert(message);

        this.error = message;
        this.loading = false;
        this.data = null;

        this.cd.detectChanges();
      }
    });
  }

  // 🔥 CLEAR ON INPUT CHANGE
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