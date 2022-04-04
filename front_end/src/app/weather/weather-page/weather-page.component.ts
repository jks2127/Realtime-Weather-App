import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import dummyData from "../../../assets/dummyData3days.json";

@Component({
  selector: 'app-weather-page',
  templateUrl: './weather-page.component.html',
  styleUrls: ['./weather-page.component.scss']
})

export class WeatherPageComponent implements OnInit {
  counter = 0 ;
  dataObj: any={
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
  toggleValue:boolean = false;
  constructor(private http: HttpClient) {
    this.sbApiCall({cityName:'bhubaneswar'});
    // console.log(dummyData);
    // this.dataObj = dummyData;
    
  }
  
  apiCall(formData: any) {
    const params = new HttpParams()
    .set('key','dd57e8592cde4ad999261418220803')
    .set('q', formData.cityName)
    .set('aqi', 'yes')
    .set('alerts','no')
    .set('days', 6);

    const data = this.http.request("GET", " http://api.weatherapi.com/v1/forecast.json", {params});

    data.subscribe((val)=>{
      this.dataObj = val;
      console.log(this.dataObj);
    });
  }

  sbApiCall(formData: any) {
    const data = this.http.request("GET", "http://localhost:8762/location/"+formData.cityName);

    data.subscribe((val)=>{
      this.dataObj = val;
    })
  }

  ngOnInit(): void {
  }

  getFormData(formData: any) {
    console.log("@output works on parent");
    
    console.log(formData);
    // this.apiCall(formData);
    this.sbApiCall(formData);

  }

  menuToggle(val:any) {
    console.log("menu button clicked weather page", val);
    this.toggleValue= val;
  }
}
