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
import { CreateMedicationComponent } from './john/medications-post/medications-post';
import { GetAllMedicationsComponent } from './john/medications-get/medications-get';
import { DeleteMedicationComponent } from './john/medicationscode-delete/medicationscode-delete';
import { GetMedicationComponent } from './john/medicationscode-get/medicationscode-get';
import { UpdateMedicationComponent } from './john/medicationscode-put/medicationscode-put';
import { CreatePrescriptionComponent } from './john/prescriptions-post/prescriptions-post';
import { GetByDateComponent } from './john/prescriptions-datefromto-get/prescriptions-datefromto-get'; 
import { GetAllPrescriptionsComponent } from './john/prescriptions-get/prescriptions-get';
import { GetByPatientComponent } from './john/prescriptions-patientssn-get/prescriptions-patientssn-get';
import { GetByPhysicianComponent } from './john/prescriptions-physicianempid-get/prescriptions-physicianempid-get';
import { GetByMedicationComponent } from './john/prescriptionscode-medication-get/prescriptionscode-medication-get'; 
import { Component } from '@angular/core'; 
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
{ path: 'medications-post', component:CreateMedicationComponent},
{ path: 'medications-get', component:GetAllMedicationsComponent},
{ path: 'medications-delete',component:DeleteMedicationComponent},
{ path: 'medications-get-by-code',component:GetMedicationComponent},
{ path: 'medications-put',component:UpdateMedicationComponent},
{ path: 'prescriptions-post',component:CreatePrescriptionComponent},
{ path: 'prescriptions-get-date-range',component:GetByDateComponent},
{ path: 'prescriptions-get',component:GetAllPrescriptionsComponent},
{ path: 'prescriptions-get-patient',component:GetByPatientComponent},
{ path: 'prescriptions-get-physician',component:GetByPhysicianComponent},
{ path: 'prescriptions-get-medication',component:GetByMedicationComponent}
];
