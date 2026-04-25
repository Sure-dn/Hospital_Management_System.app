import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-patient-post',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './patient-post.html',
  styleUrls: ['./patient-post.css']
})
export class PatientPostComponent {

  patient = {
    ssn: '',
    name: '',
    address: '',
    phone: '',
    insuranceId: '',
    pcp: ''
  };

  constructor(private http: HttpClient) {}

  submit() {
    this.http.post('http://localhost:8080/api/patients', this.patient)
      .subscribe({
        next: (res) => {
          alert('✅ Patient Added Successfully');
          console.log(res);
        },
        error: (err) => {
          alert('❌ Error while saving');
          console.error(err);
        }
      });
  }
}