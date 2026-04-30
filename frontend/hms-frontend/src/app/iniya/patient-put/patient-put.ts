import { Component } from '@angular/core'; 
import { HttpClient } from '@angular/common/http';
 import { FormsModule } from '@angular/forms'; 
 import { JsonPipe } from '@angular/common'; 
 
 @Component({
   selector: 'app-patient-put', 
   standalone: true, 
   imports: [FormsModule, JsonPipe],
    templateUrl: './patient-put.html',
     styleUrls: ['./patient-put.css'] }) 
     
 export class PatientPutComponent { 
  patient: any = {}; 
  data: any; 
  constructor(private http: HttpClient) {} 
  update() { 
    this.http.put(`http://localhost:9090/api/patients/${this.patient.ssn}`, this.patient) 
      .subscribe({ 
        next: (res: any) => { 
          this.data = res;
           alert("✅ Patient updated successfully"); 
          }, 
          error: (err) => { 
            console.log(err); 
            alert(err.error?.message || "❌ Failed to update patient"); 
          } 
        });
      } 
    }
    