import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';

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
    locationName:[''],
  })

  addLocation() {    
    this.outputFormData.emit(this.addLocationForm.value)
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
