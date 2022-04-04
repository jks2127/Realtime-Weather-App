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
  uvIndex = 0;
  currentTime: any;
  scaleAirQuality = ["Very High", "High", "Moderate", "Poor", "Very Poor"];
  scaleUvIndex = ["Good", "Moderate", "Moderate", "Unhealthy", "Very Unhealthy", "Hazardous"];
  scalePointerAirQuality = Math.round(this.airQualityIndex/40);
  scalePointerUvIndex = Math.round(this.uvIndex/2);

  constructor() { }

  ngOnInit(): void {
  }
  
  ngOnChanges() {
    this.currentTime = formatDate(new Date, 'H', 'en-IN');    

    if (this.dataObj.current.airQuality) {
      this.airQualityIndex = (this.dataObj.current.airQuality.co + this.dataObj.current.airQuality.no2 + this.dataObj.current.airQuality.o3 + this.dataObj.current.airQuality.pm25 + this.dataObj.current.airQuality.pm10 + this.dataObj.current.airQuality.so2)/6;
      this.scalePointerAirQuality = Math.trunc(this.airQualityIndex/40);
    }
    this.uvIndex = this.dataObj.current.uv;
    this.scalePointerUvIndex = Math.trunc(this.uvIndex/2);
    
    this.hProgress= `height: 8px; width: 8px; margin-left: ${(this.airQualityIndex/200)*100}%;  border: 2px solid white ;`;
    this.vProgress= `height: 7px; width: 7px; margin-top: ${(10 - this.dataObj.current.uv)*10}px; border: 2px solid white ;`;    
  }

}
