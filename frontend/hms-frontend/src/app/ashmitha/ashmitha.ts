import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ashmitha',
  imports: [],
  templateUrl: './ashmitha.html',
  styleUrl: './ashmitha.css',
})
export class Ashmitha {
  constructor(private router: Router) {}

  openEndpoint(path: string) {
    this.router.navigate([path]);
  }
}
