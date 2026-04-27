import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-treatment-post',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './treatment-post.html',
  styleUrl: './treatment-post.css'
})
export class TreatmentPostComponent {

  treatment = {
    patientId: '',
    procedureId: '',
    stayId: '',
    dateUndergoes: '',
    physicianId: '',
    nurseId: ''
  };

  message = '';
  error = '';

  constructor(private http: HttpClient) {}

  submit() {
    this.message = '';
    this.error = '';

    this.http.post('http://localhost:9090/api/treatments', {
      patientId: Number(this.treatment.patientId),
      procedureId: Number(this.treatment.procedureId),
      stayId: Number(this.treatment.stayId),
      dateUndergoes: new Date(this.treatment.dateUndergoes).toISOString(),
      physicianId: Number(this.treatment.physicianId),
      nurseId: Number(this.treatment.nurseId)
    }).subscribe({
      next: () => {
        this.message = 'Treatment added successfully ✅';
        this.clearForm();
      },
      error: (err) => {
        console.error(err);
        this.error = 'Error while saving ❌';
      }
    });
  }

  clearForm() {
    this.treatment = {
      patientId: '',
      procedureId: '',
      stayId: '',
      dateUndergoes: '',
      physicianId: '',
      nurseId: ''
    };
  }
}