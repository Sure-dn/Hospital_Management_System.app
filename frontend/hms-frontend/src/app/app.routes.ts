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
import { ProcedurePatientsComponent } from './jai/procedure-patients/procedure-patients';
import { StayPostComponent } from './jai/stay-post/stay-post';
import { ProceduresUpdateComponent } from './jai/procedures-update/procedures-update';
import { PatientStaysComponent } from './jai/patient-stays/patient-stays';
import { TreatmentPostComponent } from './jai/treatment-post/treatment-post';
import { TreatmentGetComponent } from './jai/treatment-get/treatment-get';
import { StayTreatmentsComponent } from './jai/stay-treatments/stay-treatments';
import { ProceduresCodeComponent } from './jai/procedures-code/procedures-code';


export const routes: Routes = [

  // 🌐 PUBLIC
  { path: '', component: HomeComponent },
  { path: 'login', component: Loginpage },

  // 🔐 JAI MODULE (ALL YOUR ENDPOINTS)
  {
    path: 'jai',
    component: JaiComponent,
    canActivate: [authGuard],
    data: { user: 'Jai' },

    children: [

      // PROCEDURES
      { path: 'procedures-get', component: ProceduresGetComponent },
      { path: 'procedures-post', component: ProceduresPostComponent },
      { path: 'procedures-code', component: ProceduresCodeComponent },
      { path: 'procedures-update', component: ProceduresUpdateComponent },

      // STAY
      { path: 'stay-get', component: StayGetComponent },
      { path: 'stay-post', component: StayPostComponent },
      { path: 'patient-stays', component: PatientStaysComponent },

      // TREATMENT
      { path: 'treatment-get', component: TreatmentGetComponent },
      { path: 'treatment-post', component: TreatmentPostComponent },
      { path: 'stay-treatments', component: StayTreatmentsComponent },
      { path: 'patient-treatments', component: PatientTreatmentsComponent },

      // REPORT
      { path: 'procedure-patients', component: ProcedurePatientsComponent },
      { path: 'physician-procedures', component: PhysicianProceduresComponent }

    ]
  },

  // 🔐 TEAMMATES DASHBOARDS
  { path: 'sureka', component: Sureka, canActivate: [authGuard], data: { user: 'Sureka' } },
  { path: 'iniya', component: Iniya, canActivate: [authGuard], data: { user: 'Iniya' } },
  { path: 'ashmitha', component: Ashmitha, canActivate: [authGuard], data: { user: 'Ashmitha' } },
  { path: 'john', component: John, canActivate: [authGuard], data: { user: 'John' } }

];