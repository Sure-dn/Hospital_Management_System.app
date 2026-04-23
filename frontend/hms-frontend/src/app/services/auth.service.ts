import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private api = 'http://localhost:9090/api/auth/login';

  constructor(private http: HttpClient, private router: Router) {}

  login(data: any) {
    return this.http.post(this.api, data, { responseType: 'text' });
  }

  setSession(token: string) {
    localStorage.setItem('token', token);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/']);
  }
}