import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'String interpolation';
  nameChange:any;
  emailChange:any;
  phoneChange:any;
  
  constructor( private formbuilder: FormBuilder){}
  
  userForm = this.formbuilder.group({
    name: ['dddd'],
    email: [''],
    phone: [''],
  });

  onSubmit() {
    this.userForm.valueChanges.subscribe((val: any)=>{
      console.log(val);
      
      this.nameChange = val.name;
      this.emailChange = val.email;
      this.phoneChange = val.phone;

    })
  }
}
