import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { ENDPOINTS } from "../../../environments/environment";
@Component({
  selector: 'app-weather-page',
  templateUrl: './weather-page.component.html',
  styleUrls: ['./weather-page.component.scss']
})

export class WeatherPageComponent implements OnInit {
  weatherData: any={
    'location': {
      name: "-",
      country: "-",
      region: "-",
      tzId: "-",
      lat: 0,
      lon: 0,
      localtime: "-",
      localtimeEpoch: 0,
    },
    "current": {
      airQuality: {co: 0, no2: 0, o3: 0, so2: 0, pm25: 0, usEpaIndex: 0},
      condition: {text: '-', icon: '-', code: 0},
      tempC: 0,
      tempF: 0,
      pressureIn: 0,
      pressureMb: 0,
      feelslikeC: 0,
      feelslikeF: 0,
      lastUpdated: "",
      lastUpdatedEpoch: 0,
      uv: 0,
      windDegree: 0,
      windDir: "-",
      windKph: 0,
      windMph: 0,
      cloud: 0,
      humidity: 0,
      gustKph: 0,
      gustMph: 0,
      visKm: 0,
      visMiles: 0,
      isDay: 0,
    },
    "forecast": {
      "forecastday": [
        {
          date: "",
          dateEpoch: 0,
          "day": {
            maxtempC: 0,
            maxtempF: 0,
            mintempC: 0,
            mintempF: 0,
            avgtempC: 0,
            avgtempF: 0,
            uv: 0,
            maxwindKph: 0,
            maxwindMph: 0,
            avghumidity: 0,
            avgvisKm: 0,
            avgvisMiles: 0,
            dailyChanceOfRain: 0,
            dailyChanceOfSnow: 0,
            dailyWillItRain: 0,
            dailyWillItSnow: 0,
            totalprecipIn: 0,
            totalprecipMm: 0,
            "condition": {text: "", icon: "", code: 0},
          },
          "astro": {
            sunrise: "-",
            sunset: "-",
            moonrise: "-",
            moonset: "-",
            moonPhase: "-",
            moonIllumination: "-"
          },
          "hour": [
            {
              tempC: 0,
              tempF: 0,
              feelslikeC: 0,
              feelslikeF: 0,
              time: "",
              timeEpoch: 0,
              uv: 0,
              heatindexC: 0,
              heatindexF: 0,
              pressureIn: 0,
              pressureMb: 0,
              chanceOfRain: 0,
              chanceOfSnow: 0,
              windDegree: 0,
              windDir: "",
              windKph: 0,
              windMph: 0,
              windchillC: 0,
              windchillF: 0,
              condition: {text: '-', icon: '-', code: 0},
              dewpointC: 0,
              dewpointF: 0,
              gustKph: 0,
              gustMph: 0,
              cloud: 0,
              humidity: 0,
              precipIn: 0,
              precipMm: 0,
              visKm: 0,
              visMiles: 0,
              willItRain: 0,
              willItSnow: 0,
              isDay: 0,
            },
            {
              tempC: 0,
              tempF: 0,
              feelslikeC: 0,
              feelslikeF: 0,
              time: "",
              timeEpoch: 0,
              uv: 0,
              heatindexC: 0,
              heatindexF: 0,
              pressureIn: 0,
              pressureMb: 0,
              chanceOfRain: 0,
              chanceOfSnow: 0,
              windDegree: 0,
              windDir: "",
              windKph: 0,
              windMph: 0,
              windchillC: 0,
              windchillF: 0,
              condition: {text: '-', icon: '-', code: 0},
              dewpointC: 0,
              dewpointF: 0,
              gustKph: 0,
              gustMph: 0,
              cloud: 0,
              humidity: 0,
              precipIn: 0,
              precipMm: 0,
              visKm: 0,
              visMiles: 0,
              willItRain: 0,
              willItSnow: 0,
              isDay: 0,
            },   
          ]
        },
      ]
    },     
  };
  toggleValue: boolean = false;
  errorResponse!: String;
  locationList!: Object;
  
  constructor(private http: HttpClient) {
    this.getWeatherData('bhubaneswar');   
    this.getLocationList(); 
  }

  ngOnInit(): void {}
  ngOnChanges(): void { }
  
  getWeatherData(locaitonName: String) {
    this.http.get(ENDPOINTS.GATEWAY + locaitonName).subscribe((responseData)=>{
      this.toggleValue = false;
      this.weatherData = responseData;
      this.errorResponse = '';
    })
  }

  getLocationList() {
    this.http.get(ENDPOINTS.GATEWAY + "all").subscribe(responseData =>{
      this.locationList = responseData;      
    })
  }

  addLocation(formData: any) {   
    this.http.post(ENDPOINTS.LOCATION, {'locationName': formData.locationName}).subscribe(()=>{
      this.errorResponse = '';
      this.getLocationList();
    },(err)=>{
      this.errorResponse = err.error;
    })
  }

  deleteLocation(locationId: String){
    this.http.delete(ENDPOINTS.LOCATION + locationId).subscribe(()=>{
      this.getLocationList();
    })
  }
  
  onClickLocationList(locationName: String) {
    this.getWeatherData(locationName);
  }

  menuToggle(value: boolean) {
    this.toggleValue= value;
  }
}
