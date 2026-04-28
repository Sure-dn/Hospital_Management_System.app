import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-patient-getbyssn',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './patient-getbyssn.html',
  styleUrls: ['./patient-getbyssn.css']
})
export class PatientGetBySsnComponent {

  ssn!: number;
  data: any;

  errorMessage: string = '';
  showPopup: boolean = false;

  constructor(private http: HttpClient) {}

  load() {

    if (!this.ssn) {
      this.errorMessage = "SSN is required";
      this.showPopup = true;
      return;
    }

    this.http.get(`http://localhost:9090/api/patients/${this.ssn}`)
      .subscribe({
        next: (res) => {
          this.data = res;
          this.errorMessage = '';
          this.showPopup = false;
        },
        error: (err) => {
          console.log(err);

          this.data = null;

          // ✅ get backend exception message
          this.errorMessage = err.error?.message || "Something went wrong";
          this.showPopup = true; // ✅ show popup
        }
      });
  }

  closePopup() {
    this.showPopup = false;
  }
}