import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

export const authGuard = (route: any, state: any) => {

  const auth = inject(AuthService);
  const router = inject(Router);

  if (auth.isLoggedIn()) {
    return true;
  }

  // 🔥 get correct user from route
  const user = route.data?.['user'] || '';

  // 🔥 get route path
  const fullPath = state.url.replace('/', '');

  // alert
  setTimeout(() => {
    alert("You didn't sign in ❌");
  }, 100);

  router.navigate(['/login'], {
    queryParams: {
      user: user,
      route: fullPath
    }
  });

  return false;
};