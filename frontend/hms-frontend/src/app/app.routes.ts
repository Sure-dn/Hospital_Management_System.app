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
import { CreateNurseComponent } from './sureka/nurse-post/nurse-post';
import { GetAllNursesComponent } from './sureka/nurse-get/nurse-get';
import { GetNurseByIdComponent } from './sureka/employeeid-get/employeeid-get';
import { UpdateNurseComponent } from './sureka/employeeid-put/employeeid-put';

import { OnCallPostComponent } from './sureka/oncall-post/oncall-post';

import { OnCallGetComponent } from './sureka/oncall-get/oncall-get';

import { OnCallFloorComponent } from './sureka/floorcode-get/floorcode-get';

import { OnCallDeleteComponent } from './sureka/oncall-delete/oncall-delete';

import { BlocksGetComponent } from './sureka/blocks-get/blocks-get';

import { BlockRoomsComponent } from './sureka/floorcoderoom-get/floorcoderoom-get';

import { RoomsGetComponent } from './sureka/rooms-get/rooms-get';

import { RoomGetComponent } from './sureka/roomno-get/roomno-get';

import { RoomUpdateComponent } from './sureka/roomnoavail-put/roomnoavail-put';


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
{ path:'nurse-post',component:CreateNurseComponent},
{ path:'nurse-get-all',component:GetAllNursesComponent},
{ path: 'nurse-get',component:GetNurseByIdComponent},
{path:'nurse-update',component:UpdateNurseComponent},
{path:'oncall-post',component:OnCallPostComponent},
{path:'oncall-get',component:OnCallGetComponent},
{path:'oncall-floor',component:OnCallFloorComponent},
{path:'oncall-delete',component:OnCallDeleteComponent},
{path:'blocks',component:BlocksGetComponent},
{path:'block-rooms',component:BlockRoomsComponent},
{path:'rooms',component:RoomsGetComponent},
{path:'room-get',component:RoomGetComponent},
{path:'room-update',component:RoomUpdateComponent}
];
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
