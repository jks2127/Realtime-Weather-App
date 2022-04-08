import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-right-pane',
  templateUrl: './right-pane.component.html',
  styleUrls: ['./right-pane.component.scss']
})
export class RightPaneComponent implements OnInit {
  @Output() outputFormData = new EventEmitter();
  @Output() toggleValue = new EventEmitter();
  @Input() locationList:any;
  @Output() locationName = new EventEmitter();
  @Output() locationIdToDelete = new EventEmitter();
  @Input() errorResponse:any;

  showLoader:any = {
    flag: false,
    id:''
  };

  constructor(private formBuilder: FormBuilder) { }
  ngOnInit(): void { }
  ngOnChanges() { }

  addLocationForm = this.formBuilder.group({
    locationName:['', Validators.required],
  })

  addLocation() {
    if(this.addLocationForm.valid) {
      this.outputFormData.emit(this.addLocationForm.value)
      this.addLocationForm.reset(true);
    }else {
      this.errorResponse = "Invalid Location Name";
    }
  }

  deleteLocation(locationId : String) {
    this.showLoader.flag = true;
    this.showLoader.id = locationId;
    this.locationIdToDelete.emit(locationId);
  }

  onClickLocationList(locationName:String){
    this.locationName.emit(locationName);
  }

  close() {
    this.toggleValue.emit(false);
  }
}
