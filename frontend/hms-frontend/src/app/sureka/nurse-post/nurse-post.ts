import { Component } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-create-nurse',
  standalone: true,
  imports: [FormsModule, HttpClientModule, JsonPipe],
  templateUrl: './nurse-post.html'
})
export class CreateNurseComponent {

  nurse = {
    name: '',
    position: '',
    registered: true,
    ssn: ''
  };

  data: any;
  message: string = '';

  constructor(private http: HttpClient) {}

  create() {
    console.log("Button clicked", this.nurse);

    this.http.post('http://localhost:9090/api/nurses', this.nurse)
      .subscribe({
        next: (res) => {
          this.data = res;
          this.message = "✅ Nurse created successfully";

          // reset form
          this.nurse = {
            name: '',
            position: '',
            registered: true,
            ssn: ''
          };
        },
        error: (err) => {
          console.error(err);
          this.message = "❌ Error creating nurse";
        }
      });
  }
}