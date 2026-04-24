import { Routes } from '@angular/router';
import { HomeComponent } from './landingpages/homepage/homepage';
import { Loginpage } from './landingpages/loginpage/loginpage';
import { Sureka } from './sureka/sureka';
import { authGuard } from './services/auth.guard';
import { JaiComponent } from './jai/jai';
import { Iniya } from './iniya/iniya';
import { Ashmitha } from './ashmitha/ashmitha';
import { JohnComponent } from './john/john';
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
{ path: 'john', component: JohnComponent, canActivate: [authGuard] },
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
