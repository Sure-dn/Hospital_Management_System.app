import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { JsonPipe,NgIf ,NgFor} from '@angular/common';

@Component({
  selector: 'app-get-all-nurses',
  standalone: true,
  imports: [FormsModule, NgIf, NgFor],
  templateUrl: './nurse-get.html',
  styleUrls: ['./nurse-get.css'] 
})
export class GetAllNursesComponent {

  data: any = { data: [] };

  constructor(private http: HttpClient) {}

  getAll() {
    this.http.get('http://localhost:9090/api/nurses')
      .subscribe({
        next: (res: any) => {
          console.log(res); // ✅ check response
          this.data = res;
        },
        error: err => console.error(err)
      });
  }
}