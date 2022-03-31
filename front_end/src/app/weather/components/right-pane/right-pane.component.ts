import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-right-pane',
  templateUrl: './right-pane.component.html',
  styleUrls: ['./right-pane.component.scss']
})
export class RightPaneComponent implements OnInit {
  @Output() outputFormData = new EventEmitter();
  @Output() toggleValue = new EventEmitter();

  constructor(private formBuilder: FormBuilder) { }

  weatherForm = this.formBuilder.group({
    cityName:[''],
    pinCode:[''],
  })

  ngOnInit(): void {
  }

  weatherFormSubmit() {
    console.log("weather form submitted", this.weatherForm.value);
    this.outputFormData.emit(this.weatherForm.value)
  }
  close() {
    console.log("close button clicked");
    this.toggleValue.emit(false);
    
  }
}
