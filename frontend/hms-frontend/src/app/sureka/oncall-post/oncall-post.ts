import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe } from '@angular/common';

@Component({
  selector: 'app-oncall-post',
  standalone: true,
  imports: [FormsModule, JsonPipe],
  templateUrl: './oncall-post.html'
})
export class OnCallPostComponent {

  employeeId!: number;

  oncall = {
    blockFloor: '',
    blockCode: '',
    onCallStart: '',
    onCallEnd: ''
  };

  data: any;

  constructor(private http: HttpClient) {}

  assign() {
    this.http.post(`http://localhost:9090/api/nurses/${this.employeeId}/on-call`, this.oncall)
      .subscribe({
        next: res => this.data = res,
        error: err => console.error(err)
      });
  }
}
