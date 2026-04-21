import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-jai',
  standalone: true,
  templateUrl: './jai.html',
  styleUrls: ['./jai.css'],
})
export class JaiComponent {

  constructor(private router: Router) {}

 openEndpoint(path: string) {
  console.log("Navigating to:", path);
  this.router.navigate([path]);
 }
}