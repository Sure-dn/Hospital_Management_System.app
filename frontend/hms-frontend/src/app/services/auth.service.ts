import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private api = 'http://localhost:9090/api/auth/login';

  constructor(private http: HttpClient, private router: Router) {}

  login(data: any) {
    return this.http.post<any>(this.api, data);
  }

  setSession(role: string) {
    localStorage.setItem('role', role);
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('role') !== null;
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}