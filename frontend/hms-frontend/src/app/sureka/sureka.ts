import { Component } from '@angular/core';
import { Router } from '@angular/router';
@Component({
  selector: 'app-sureka',
 standalone:true,
  templateUrl: './sureka.html',
  styleUrl: './sureka.css',
})
export class Sureka {
   constructor(private router: Router) {}

  openEndpoint(path: string) {
    this.router.navigate([path]);
  }
}
