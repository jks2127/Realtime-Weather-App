import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html',
  styleUrls: ['./graphs.component.scss']
})
export class GraphsComponent implements OnInit {
  @Input() dataObj?: any;
  vProgress= `height: 7px; width: 7px; margin-top: ${100}px; border: 2px solid white ;`;
  hProgress= `height: 8px; width: 8px; margin-left: ${0}%;  border: 2px solid white ;`;
  airQualityIndex = 0;
  currentTime: any;

  constructor() { }

  ngOnInit(): void {
  }
  
  ngOnChanges() {
    this.currentTime = formatDate(new Date, 'H', 'en-IN');    
    
    if (this.dataObj.current.air_quality) {
      this.airQualityIndex = (this.dataObj.current.air_quality.co + this.dataObj.current.air_quality.no2 + this.dataObj.current.air_quality.o3 + this.dataObj.current.air_quality.pm2_5 + this.dataObj.current.air_quality.pm10 + this.dataObj.current.air_quality.so2)/6;
    }
    this.hProgress= `height: 8px; width: 8px; margin-left: ${(this.airQualityIndex/500)*100}%;  border: 2px solid white ;`;
    this.vProgress= `height: 7px; width: 7px; margin-top: ${(10 - this.dataObj.current.uv)*10}px; border: 2px solid white ;`;    
  }

}
