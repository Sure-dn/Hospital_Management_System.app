import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-procedures-post',
  imports: [FormsModule],
  templateUrl: './procedures-post.html',
  styleUrl: './procedures-post.css'
})
export class ProceduresPostComponent {

  procedure = {
    code: '',
    name: '',
    cost: ''
  };

  constructor(private http: HttpClient) {}

  submit() {
    this.http.post('http://localhost:9090/api/procedures', this.procedure)
      .subscribe({
        next: () => {
          alert("Saved Successfully ✅");
          this.procedure = { code: '', name: '', cost: '' };
        },
        error: (err) => {
          console.log(err);
          alert(err.error.message);
        }
      });
  }
}