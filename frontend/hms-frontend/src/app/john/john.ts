import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-john',
  standalone: true,
 
  
templateUrl: './john.html',
  styleUrls: ['./john.css']   // ← use your existing dashboard CSS file here
})
export class JohnComponent {

constructor(private router:Router){}
  openEndpoint(path: string) {
   this.router.navigate([path]);
    // You can expand this later (e.g. router navigation or dynamic component loading)
  }
}
