import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';

// ✅ IMPORT YOUR INTERCEPTOR
import { authInterceptor } from './services/interceptors/auth-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    
    provideRouter(routes),

    // ✅ FIX HERE
    provideHttpClient(
      withInterceptors([authInterceptor])
    )
  ]
};