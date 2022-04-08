import { formatDate } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html',
  styleUrls: ['./graphs.component.scss']
})
export class GraphsComponent implements OnInit {
  @Input() weatherData?: any;
  vProgress = `height: 7px; width: 7px; margin-top: ${100}px; border: 2px solid white ;`;
  hProgress = `height: 8px; width: 8px; margin-left: ${0}%;  border: 2px solid white ;`;
  airQualityIndex = 0;
  uvIndex = 0;
  currentTime: any;
  //Below 4 lines are For showing dynamic status of Air quality and UV index based on values
  scaleAirQuality = ["Very High", "High", "Moderate", "Poor", "Very Poor", "Very Poor"];
  scaleUvIndex = ["Good", "Moderate", "Moderate", "Unhealthy", "Very Unhealthy", "Hazardous"];
  scalePointerAirQuality = Math.round(this.airQualityIndex/40);
  scalePointerUvIndex = Math.round(this.uvIndex/2);

  constructor() { }

  ngOnInit(): void {
  }
  
  ngOnChanges() {
    this.currentTime = formatDate(new Date, 'H', 'en-IN');    

    if (this.weatherData.current.airQuality) {
      this.airQualityIndex = (this.weatherData.current.airQuality.co + this.weatherData.current.airQuality.no2 + this.weatherData.current.airQuality.o3 + this.weatherData.current.airQuality.pm25 + this.weatherData.current.airQuality.pm10 + this.weatherData.current.airQuality.so2)/6;
      this.airQualityIndex > 200 ? this.airQualityIndex=200 : {} ;
      this.scalePointerAirQuality = Math.trunc(this.airQualityIndex/40);
    }
    this.uvIndex = this.weatherData.current.uv;
    this.uvIndex > 10 ? this.uvIndex = 10 : {};
    this.scalePointerUvIndex = Math.trunc(this.uvIndex/2);
    
    // calculating the progress percentage based on values coming each time when a weather data is fetched from API
    this.hProgress= `height: 8px; width: 8px; margin-left: ${(this.airQualityIndex/200)*100}%;  border: 2px solid white ;`;
    this.vProgress= `height: 7px; width: 7px; margin-top: ${(10 - this.weatherData.current.uv)*10}px; border: 2px solid white ;`;    
  }

}
