import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-procedures-update',
  standalone: true,
  imports: [FormsModule, NgIf],
  templateUrl: './procedures-update.html',
  styleUrl: './procedures-update.css'
})
export class ProceduresUpdateComponent {

  procedure = {
    code: '',
    name: '',
    cost: ''
  };

  message = '';
  error = '';

  constructor(private http: HttpClient) {}

  update() {
  this.http.put(
    `http://localhost:9090/api/procedures/${this.procedure.code}`,
    {
      name: this.procedure.name,
      cost: Number(this.procedure.cost)
    }
  ).subscribe({
    next: () => {
      alert("✅ Procedure updated successfully");
    },
    error: (err) => {
      console.error(err);
      alert(err.error.message);
    }
  });
}
}