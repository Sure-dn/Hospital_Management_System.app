import { ChangeDetectorRef, Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf, NgFor ,DatePipe} from '@angular/common';

@Component({
  selector: 'app-physician-procedures',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor, DatePipe],
  templateUrl: './physician-procedures.html',
  styleUrl: './physician-procedures.css'
})
export class PhysicianProceduresComponent {

  physicianId: any;
  procedures: any[] = [];
  error: string = '';
  hasSearched = false;

  constructor(private http: HttpClient, private cd: ChangeDetectorRef) {}

  loading = false;

fetch() {
  this.hasSearched = true;
  this.error = '';
  this.loading = true;

  // 🔥 RESET OLD DATA
  this.procedures = [];

  const token = localStorage.getItem('token');

  this.http.get<any[]>(
    `http://localhost:9090/api/reports/physician/${this.physicianId}/procedures`,
    {
      headers: {
        Authorization: 'Bearer ' + token
      }
    }
  ).subscribe({
    next: (res) => {
  console.log("DATA:", res);

  this.procedures = [...res];
  this.loading = false;

  if (this.procedures.length === 0) {
    this.error = 'No procedures found';
  } else {
    this.error = '';
  }

  this.cd.detectChanges(); // 🔥 VERY IMPORTANT
},
    error: (err) => {
  console.error(err);

  let msg = "Something went wrong";

  if (typeof err.error === 'string') {
    msg = err.error;
  } else if (err.error?.message) {
    msg = err.error.message;
  }
  alert(err.error.message); // ✅ popup alert

  this.error = msg;
  this.loading = false;
  this.procedures = [];

  this.cd.detectChanges(); // 🔥 VERY IMPORTANT
}
  });
}
onIdChange() {
  if (this.hasSearched) {
    this.procedures = [];
    this.error = '';
    this.hasSearched = false;
    this.loading = false;
  }
}
}