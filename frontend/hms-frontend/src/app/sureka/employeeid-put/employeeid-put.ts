import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-update-nurse',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './employeeid-put.html'
})
export class UpdateNurseComponent {

  id!: number;

  nurse = {
    name: '',
    position: '',
    registered: true,
    ssn: ''
  };

  data: any;

  constructor(private http: HttpClient) {}

  update() {
    this.http.put(`http://localhost:9090/api/nurses/${this.id}`, this.nurse)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}
