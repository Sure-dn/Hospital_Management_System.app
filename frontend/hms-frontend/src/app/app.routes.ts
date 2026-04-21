import { Routes } from '@angular/router';
import { HomeComponent } from './landingpages/homepage/homepage';
import { Loginpage } from './landingpages/loginpage/loginpage';
import { Sureka } from './sureka/sureka';
import { authGuard } from './services/auth.guard';
import { JaiComponent } from './jai/jai';
import { Iniya } from './iniya/iniya';
import { Ashmitha } from './ashmitha/ashmitha';
import { John } from './john/john';
import { ProceduresGetComponent } from './jai/procedures-get/procedures-get';
import { ProceduresPostComponent } from './jai/procedures-post/procedures-post';
import { StayGetComponent } from './jai/stay-get/stay-get';
import { PatientTreatmentsComponent } from './jai/patient-treatments/patient-treatments';
import { PhysicianProceduresComponent } from './jai/physician-procedures/physician-procedures';


export const routes: Routes = [
{ path: '', component: HomeComponent },
{ path: 'login', component: Loginpage },

// protected pages
{ path: 'sureka', component: Sureka, canActivate: [authGuard] },
{ path: 'jai', component: JaiComponent, canActivate: [authGuard] },
{ path: 'iniya', component: Iniya, canActivate: [authGuard] },
{ path: 'ashmitha', component: Ashmitha, canActivate: [authGuard] },
{ path: 'john', component: John, canActivate: [authGuard] },
{ path: 'procedures-get', component: ProceduresGetComponent, canActivate: [authGuard] },
{ path: 'procedures-post', component: ProceduresPostComponent, },
{ path: 'stay-get', component: StayGetComponent },
{ path: 'patient-treatments', component: PatientTreatmentsComponent },
{ path: 'physician-procedures', component: PhysicianProceduresComponent },
];
