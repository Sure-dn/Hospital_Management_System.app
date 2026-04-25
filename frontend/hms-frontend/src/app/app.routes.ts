import { Routes } from '@angular/router';
import { HomeComponent } from './landingpages/homepage/homepage';
import { Loginpage } from './landingpages/loginpage/loginpage';
import { Sureka } from './sureka/sureka';
import { authGuard } from './services/auth.guard';
import { JaiComponent } from './jai/jai';
import { Iniya } from './iniya/iniya';
import { Ashmitha } from './ashmitha/ashmitha';
import { JohnComponent } from './john/john';


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

  // JOHN MODULE
// {
//   path: 'john',
//   component: JohnComponent,
//   canActivate: [authGuard],
//   data: { user: 'John' },

//   children: [

//     // 🔥 DEFAULT DASHBOARD
//     {
//       path: '',
//       loadComponent: () =>
//         import('./john/dashboard/dashboard')
//           .then(m => m.DashboardComponent)
//     },

//     // ===== MEDICATIONS =====
//     {
//       path: 'medications-post',
//       loadComponent: () =>
//         import('./john/medications-post/medications-post')
//           .then(m => m.CreateMedicationComponent)
//     },
//     {
//       path: 'medications-get',
//       loadComponent: () =>
//         import('./john/medications-get/medications-get')
//           .then(m => m.GetAllMedicationsComponent)
//     },
//     {
//       path: 'medications-get-by-code',
//       loadComponent: () =>
//         import('./john/medicationscode-get/medicationscode-get')
//           .then(m => m.GetMedicationComponent)
//     },
//     {
//       path: 'medications-put',
//       loadComponent: () =>
//         import('./john/medicationscode-put/medicationscode-put')
//           .then(m => m.UpdateMedicationComponent)
//     },
//     {
//       path: 'medications-delete',
//       loadComponent: () =>
//         import('./john/medicationscode-delete/medicationscode-delete')
//           .then(m => m.DeleteMedicationComponent)
//     },

//     // ===== PRESCRIPTIONS =====
//     {
//       path: 'prescriptions-post',
//       loadComponent: () =>
//         import('./john/prescriptions-post/prescriptions-post')
//           .then(m => m.CreatePrescriptionComponent)
//     },
//     {
//       path: 'prescriptions-get',
//       loadComponent: () =>
//         import('./john/prescriptions-get/prescriptions-get')
//           .then(m => m.GetAllPrescriptionsComponent)
//     },
//     {
//       path: 'prescriptions-get-date-range',
//       loadComponent: () =>
//         import('./john/prescriptions-datefromto-get/prescriptions-datefromto-get')
//           .then(m => m.GetByDateComponent)
//     },
//     {
//       path: 'prescriptions-get-patient',
//       loadComponent: () =>
//         import('./john/prescriptions-patientssn-get/prescriptions-patientssn-get')
//           .then(m => m.GetByPatientComponent)
//     },
//     {
//       path: 'prescriptions-get-physician',
//       loadComponent: () =>
//         import('./john/prescriptions-physicianempid-get/prescriptions-physicianempid-get')
//           .then(m => m.GetByPhysicianComponent)
//     },
//     {
//       path: 'prescriptions-get-medication',
//       loadComponent: () =>
//         import('./john/prescriptionscode-medication-get/prescriptionscode-medication-get')
//           .then(m => m.GetByMedicationComponent)
//     }

//   ]
// },

  //ASHMITHA MODULE
//   {
//   path: 'ashmitha',
//   component: Ashmitha,
//   canActivate: [authGuard],
//   data: { user: 'Ashmitha' },

//   children: [

//     // 🔥 DEFAULT DASHBOARD
//     {
//       path: '',
//       loadComponent: () =>
//         import('./ashmitha/dashboard/dashboard')
//           .then(m => m.DashboardComponent)
//     },

//     // ===== PHYSICIAN =====
//     {
//       path: 'physician-post',
//       loadComponent: () =>
//         import('./ashmitha/physician-post/physician-post')
//           .then(m => m.PhysicianPost)
//     },
//     {
//       path: 'physician-get',
//       loadComponent: () =>
//         import('./ashmitha/physician-get/physician-get')
//           .then(m => m.PhysicianGet)
//     },
//     {
//       path: 'physician-getbyid',
//       loadComponent: () =>
//         import('./ashmitha/physician-getbyid/physician-getbyid')
//           .then(m => m.PhysicianGetbyId)
//     },
//     {
//       path: 'physician-update',
//       loadComponent: () =>
//         import('./ashmitha/physician-update/physician-update')
//           .then(m => m.PhysicianUpdate)
//     },
//     {
//       path: 'physician-delete',
//       loadComponent: () =>
//         import('./ashmitha/physician-delete/physician-delete')
//           .then(m => m.PhysicianDelete)
//     },

//     // ===== DEPARTMENT =====
//     {
//       path: 'department-post',
//       loadComponent: () =>
//         import('./ashmitha/department-post/department-post')
//           .then(m => m.DepartmentPost)
//     },
//     {
//       path: 'department-getall',
//       loadComponent: () =>
//         import('./ashmitha/department-getall/department-getall')
//           .then(m => m.DepartmentGetall)
//     },
//     {
//       path: 'department-getbyid',
//       loadComponent: () =>
//         import('./ashmitha/department-getbyid/department-getbyid')
//           .then(m => m.DepartmentGetbyID)
//     },
//     {
//       path: 'department-update',
//       loadComponent: () =>
//         import('./ashmitha/department-update/department-update')
//           .then(m => m.DepartmentUpdate)
//     },
//     {
//       path: 'department-gethead',
//       loadComponent: () =>
//         import('./ashmitha/department-gethead/department-gethead')
//           .then(m => m.DepartmentGethead)
//     },

//     // ===== AFFILIATION =====
//     {
//       path: 'affiliation-post',
//       loadComponent: () =>
//         import('./ashmitha/affiliation-post/affiliation-post')
//           .then(m => m.AffiliationPost)
//     },
//     {
//       path: 'affiliation-getbyphysician',
//       loadComponent: () =>
//         import('./ashmitha/affiliation-getbyphysician/affiliation-getbyphysician')
//           .then(m => m.AffiliationGetbyphysician)
//     },
//     {
//       path: 'affiliation-getbydepartment',
//       loadComponent: () =>
//         import('./ashmitha/affiliation-getbydepartment/affiliation-getbydepartment')
//           .then(m => m.AffiliationGetbydepartment)
//     },

//     // ===== TRAINING =====
//     {
//       path: 'training-post',
//       loadComponent: () =>
//         import('./ashmitha/training-post/training-post')
//           .then(m => m.TrainingPost)
//     },
//     {
//       path: 'training-getall',
//       loadComponent: () =>
//         import('./ashmitha/training-getall/training-getall')
//           .then(m => m.TrainingGetall)
//     },
//     {
//       path: 'training-valid',
//       loadComponent: () =>
//         import('./ashmitha/training-valid/training-valid')
//           .then(m => m.TrainingValid)
//     }

//   ]
//   },

  // SUREKA MODULE
// {
//   path: 'sureka',
//   component: Sureka,
//   canActivate: [authGuard],
//   data: { user: 'Sureka' },

//   children: [

//     // 🔥 DEFAULT DASHBOARD
//     {
//       path: '',
//       loadComponent: () =>
//         import('./sureka/dashboard/dashboard')
//           .then(m => m.DashboardComponent)
//     },

//     // ===== NURSE =====
//     {
//       path: 'nurse-post',
//       loadComponent: () =>
//         import('./sureka/nurse-post/nurse-post')
//           .then(m => m.CreateNurseComponent)
//     },
//     {
//       path: 'nurse-get',
//       loadComponent: () =>
//         import('./sureka/nurse-get/nurse-get')
//           .then(m => m.GetAllNursesComponent)
//     },

//     // ===== EMPLOYEE =====
//     {
//       path: 'employee-get',
//       loadComponent: () =>
//         import('./sureka/employeeid-get/employeeid-get')
//           .then(m => m.GetNurseByIdComponent)
//     },
//     {
//       path: 'employee-update',
//       loadComponent: () =>
//         import('./sureka/employeeid-put/employeeid-put')
//           .then(m => m.UpdateNurseComponent)
//     },

//     // ===== ONCALL =====
//     {
//       path: 'oncall-post',
//       loadComponent: () =>
//         import('./sureka/oncall-post/oncall-post')
//           .then(m => m.OnCallPostComponent)
//     },
//     {
//       path: 'oncall-get',
//       loadComponent: () =>
//         import('./sureka/oncall-get/oncall-get')
//           .then(m => m.OnCallGetComponent)
//     },
//     {
//       path: 'oncall-delete',
//       loadComponent: () =>
//         import('./sureka/oncall-delete/oncall-delete')
//           .then(m => m.OnCallDeleteComponent)
//     },

//     // ===== BLOCKS =====
//     {
//       path: 'blocks-get',
//       loadComponent: () =>
//         import('./sureka/blocks-get/blocks-get')
//           .then(m => m.BlocksGetComponent)
//     },

//     // ===== FLOOR / ROOM COMBO =====
//     {
//       path: 'floor-get',
//       loadComponent: () =>
//         import('./sureka/floorcode-get/floorcode-get')
//           .then(m => m.OnCallFloorComponent)
//     },
//     {
//       path: 'floor-room-get',
//       loadComponent: () =>
//         import('./sureka/floorcoderoom-get/floorcoderoom-get')
//           .then(m => m.BlockRoomsComponent)
//     },

//     // ===== ROOMS =====
//     {
//       path: 'rooms-get',
//       loadComponent: () =>
//         import('./sureka/rooms-get/rooms-get')
//           .then(m => m.RoomsGetComponent)
//     },
//     {
//       path: 'room-get',
//       loadComponent: () =>
//         import('./sureka/roomno-get/roomno-get')
//           .then(m => m.RoomGetComponent)
//     },
//     {
//       path: 'room-update',
//       loadComponent: () =>
//         import('./sureka/roomnoavail-put/roomnoavail-put')
//           .then(m => m.RoomUpdateComponent)
//     }

//   ]
// }
{
  path: 'iniya',
  component: Iniya,
  canActivate: [authGuard],
  data: { user: 'Iniya' },

  children: [

    // 🔥 DEFAULT DASHBOARD
    {
      path: '',
      loadComponent: () =>
        import('./iniya/dashboard/dashboard')
          .then(m => m.DashboardComponent)
    },

    // ===== PATIENT =====
    {
      path: 'patient-get',
      loadComponent: () =>
        import('./iniya/patient-get/patient-get')
          .then(m => m.PatientGet)
    },
    {
      path: 'patient-post',
      loadComponent: () =>
        import('./iniya/patient-post/patient-post')
          .then(m => m.PatientPostComponent)
    },
    {
      path: 'patient-put',
      loadComponent: () =>
        import('./iniya/patient-put/patient-put')
          .then(m => m.PatientPutComponent)
    },
    {
      path: 'patient-getbyssn',
      loadComponent: () =>
        import('./iniya/patient-getbyssn/patient-getbyssn')
          .then(m => m.PatientGetBySsnComponent)
    },
    {
      path: 'patient-delete',
      loadComponent: () =>
        import('./iniya/patient-delete/patient-delete')
          .then(m => m.PatientDeleteComponent)
    },
    {
      path: 'patient-getbyphysician',
      loadComponent: () =>
        import('./iniya/patient-getbyphysician/patient-getbyphysician')
          .then(m => m.PatientGetPcpComponent)
    },
    {
      path: 'patient-putbyphysician',
      loadComponent: () =>
        import('./iniya/patient-putbyphysician/patient-putbyphysician')
          .then(m => m.PatientPutPcpComponent)
    },

    // ===== APPOINTMENT =====
    {
      path: 'appointment-post',
      loadComponent: () =>
        import('./iniya/appointment-post/appointment-post')
          .then(m => m.AppointmentPostComponent)
    },
    {
      path: 'appointment-get',
      loadComponent: () =>
        import('./iniya/appointment-get/appointment-get')
          .then(m => m.AppointmentGetComponent)
    },
    {
      path: 'appointment-getbyid',
      loadComponent: () =>
        import('./iniya/appointment-getbyid/appointment-getbyid')
          .then(m => m.AppointmentGetByIdComponent)
    },
    {
      path: 'appointment-getbydate',
      loadComponent: () =>
        import('./iniya/appointment-getbydate/appointment-getbydate')
          .then(m => m.AppointmentGetByDateComponent)
    },
    {
      path: 'appointment-putbyid',
      loadComponent: () =>
        import('./iniya/appointment-putbyid/appointment-putbyid')
          .then(m => m.AppointmentPutComponent)
    },
    {
      path: 'appointment-deletebyid',
      loadComponent: () =>
        import('./iniya/appointment-deletebyid/appointment-deletebyid')
          .then(m => m.AppointmentDeleteComponent)
    },
    {
      path: 'appointment-getbyssn',
      loadComponent: () =>
        import('./iniya/appointment-getbyssn/appointment-getbyssn')
          .then(m => m.AppointmentGetBySsnComponent)
    },
    {
      path: 'appointment-getbyphysicianid',
      loadComponent: () =>
        import('./iniya/appointment-getbyphysicianid/appointment-getbyphysicianid')
          .then(m => m.AppointmentGetByPhysicianComponent)
    }

  ]
}

];