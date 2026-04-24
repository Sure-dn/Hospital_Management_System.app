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

  // 🔥 DEFAULT DASHBOARD
  {
    path: '',
    loadComponent: () =>
      import('./jai/dashboard/dashboard')
        .then(m => m.DashboardComponent)
  },

  // ===== PROCEDURES =====
  {
    path: 'procedures-post',
    loadComponent: () =>
      import('./jai/procedures-post/procedures-post')
        .then(m => m.ProceduresPostComponent)
  },
  {
    path: 'procedures-get',
    loadComponent: () =>
      import('./jai/procedures-get/procedures-get')
        .then(m => m.ProceduresGetComponent)
  },
  {
    path: 'procedures-code',
    loadComponent: () =>
      import('./jai/procedures-code/procedures-code')
        .then(m => m.ProceduresCodeComponent)
  },
  {
    path: 'procedures-update',
    loadComponent: () =>
      import('./jai/procedures-update/procedures-update')
        .then(m => m.ProceduresUpdateComponent)
  },

  // ===== STAY =====
  {
    path: 'stay-get',
    loadComponent: () =>
      import('./jai/stay-get/stay-get')
        .then(m => m.StayGetComponent)
  },
  {
    path: 'stay-post',
    loadComponent: () =>
      import('./jai/stay-post/stay-post')
        .then(m => m.StayPostComponent)
  },
  {
    path: 'patient-stays',
    loadComponent: () =>
      import('./jai/patient-stays/patient-stays')
        .then(m => m.PatientStaysComponent)
  },

  // ===== TREATMENT =====
  {
    path: 'treatment-get',
    loadComponent: () =>
      import('./jai/treatment-get/treatment-get')
        .then(m => m.TreatmentGetComponent)
  },
  {
    path: 'treatment-post',
    loadComponent: () =>
      import('./jai/treatment-post/treatment-post')
        .then(m => m.TreatmentPostComponent)
  },
  {
    path: 'stay-treatments',
    loadComponent: () =>
      import('./jai/stay-treatments/stay-treatments')
        .then(m => m.StayTreatmentsComponent)
  },
  {
    path: 'patient-treatments',
    loadComponent: () =>
      import('./jai/patient-treatments/patient-treatments')
        .then(m => m.PatientTreatmentsComponent)
  },

  // ===== REPORT =====
  {
    path: 'procedure-patients',
    loadComponent: () =>
      import('./jai/procedure-patients/procedure-patients')
        .then(m => m.ProcedurePatientsComponent)
  },
  {
    path: 'physician-procedures',
    loadComponent: () =>
      import('./jai/physician-procedures/physician-procedures')
        .then(m => m.PhysicianProceduresComponent)
  }

  ]
  },

  // 🔐 TEAMMATES DASHBOARDS
  { path: 'sureka', component: Sureka, canActivate: [authGuard], data: { user: 'Sureka' } },
  { path: 'iniya', component: Iniya, canActivate: [authGuard], data: { user: 'Iniya' } },
  { path: 'ashmitha', component: Ashmitha, canActivate: [authGuard], data: { user: 'Ashmitha' } },
  { path: 'john', component: John, canActivate: [authGuard], data: { user: 'John' } }

];