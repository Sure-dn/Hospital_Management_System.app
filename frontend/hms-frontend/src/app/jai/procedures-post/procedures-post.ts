import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-procedures-post',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './procedures-post.html'
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
        next: (res) => {
          alert('✅ Procedure Added Successfully');
          console.log(res);
        },
        error: (err) => {
          alert('❌ Error while saving');
          console.error(err);
        }
      });
  }
}