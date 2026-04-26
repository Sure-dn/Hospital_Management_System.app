import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-john',
  standalone: true,
 
  
templateUrl: './john.html',
  styleUrls: ['./john.css']   // ← use your existing dashboard CSS file here
  ,
  imports: [RouterOutlet]
})
export class JohnComponent {

constructor(private router:Router){}
  
}
