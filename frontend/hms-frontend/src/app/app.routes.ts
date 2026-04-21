import { Routes } from '@angular/router';
import { HomeComponent } from './landingpages/homepage/homepage';
import { Loginpage } from './landingpages/loginpage/loginpage';
import { Sureka } from './sureka/sureka';
import { authGuard } from './services/auth.guard';
import { JaiComponent } from './jai/jai';
import { Iniya } from './iniya/iniya';
import { Ashmitha } from './ashmitha/ashmitha';
import { John } from './john/john';


export const routes: Routes = [
{ path: '', component: HomeComponent },
{ path: 'login', component: Loginpage },

// protected pages
{ path: 'sureka', component: Sureka, canActivate: [authGuard] },
{ path: 'jai', component: JaiComponent, canActivate: [authGuard] },
{ path: 'iniya', component: Iniya, canActivate: [authGuard] },
{ path: 'ashmitha', component: Ashmitha, canActivate: [authGuard] },
{ path: 'john', component: John, canActivate: [authGuard] },
];
