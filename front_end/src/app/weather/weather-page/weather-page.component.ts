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
      'name': "-",
      'country': "-",
      'localtime': "-"
    },
    "current": {
      "last_updated_epoch": 0,
      "last_updated": "-",
      "temp_c": '-',
      "is_day": 1,
      "wind_kph": 0,
      "wind_degree": 0,
      "wind_dir": "-",
      "pressure_mb": 0,
      "pressure_in": 29.88,
      "humidity": '-',
      "cloud": 0,
      "feelslike_c": '-',
    },
    "forecast": {
      "forecastday": [
        {
          "date": "2022-03-08",
          "date_epoch": 1646697600,
          "day": {
            "maxtemp_c": 38.2,
            "maxtemp_f": 100.8,
            "mintemp_c": 21.7,
            "mintemp_f": 71.1,
            "avgtemp_c": 28.2,
            "avgtemp_f": 82.8,
            "maxwind_mph": 10.3,
            "maxwind_kph": 16.6,
            "totalprecip_mm": 0.0,
            "totalprecip_in": 0.0,
            "avgvis_km": 10.0,
            "avgvis_miles": 6.0,
            "avghumidity": 58.0,
            "daily_will_it_rain": 0,
            "daily_chance_of_rain": 0,
            "daily_will_it_snow": 0,
            "daily_chance_of_snow": 0,
            "condition": {
                "text": "Sunny",
                "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
                "code": 1000
            },
            "uv": 11.0
          },
          "astro": {
            "sunrise": "06:01 AM",
            "sunset": "05:54 PM",
            "moonrise": "09:37 AM",
            "moonset": "11:00 PM",
            "moon_phase": "Waxing Crescent",
            "moon_illumination": "34"
          },
          "hour": [
            {
              "time_epoch": 1646677800,
              "time": "2022-03-08 00:00",
              "temp_c": 23.2,
              "temp_f": 73.8,
              "is_day": 0,
              "condition": {
                  "text": "Clear",
                  "icon": "//cdn.weatherapi.com/weather/64x64/night/113.png",
                  "code": 1000
              },
              "wind_mph": 4.7,
              "wind_kph": 7.6,
              "wind_degree": 215,
              "wind_dir": "SW",
              "pressure_mb": 1012.0,
              "pressure_in": 29.89,
              "precip_mm": 0.0,
              "precip_in": 0.0,
              "humidity": 75,
              "cloud": 0,
              "feelslike_c": 25.1,
              "feelslike_f": 77.2,
              "windchill_c": 23.2,
              "windchill_f": 73.8,
              "heatindex_c": 25.1,
              "heatindex_f": 77.2,
              "dewpoint_c": 18.4,
              "dewpoint_f": 65.1,
              "will_it_rain": 0,
              "chance_of_rain": 0,
              "will_it_snow": 0,
              "chance_of_snow": 0,
              "vis_km": 10.0,
              "vis_miles": 6.0,
              "gust_mph": 8.9,
              "gust_kph": 14.4,
              "uv": 1.0
            },
            {
              "time_epoch": 1646681400,
              "time": "2022-03-08 01:00",
              "temp_c": 22.9,
              "temp_f": 73.2,
              "is_day": 0,
              "condition": {
                  "text": "Clear",
                  "icon": "//cdn.weatherapi.com/weather/64x64/night/113.png",
                  "code": 1000
              },
              "wind_mph": 4.7,
              "wind_kph": 7.6,
              "wind_degree": 217,
              "wind_dir": "SW",
              "pressure_mb": 1011.0,
              "pressure_in": 29.86,
              "precip_mm": 0.0,
              "precip_in": 0.0,
              "humidity": 77,
              "cloud": 0,
              "feelslike_c": 25.0,
              "feelslike_f": 77.0,
              "windchill_c": 22.9,
              "windchill_f": 73.2,
              "heatindex_c": 25.0,
              "heatindex_f": 77.0,
              "dewpoint_c": 18.7,
              "dewpoint_f": 65.7,
              "will_it_rain": 0,
              "chance_of_rain": 0,
              "will_it_snow": 0,
              "chance_of_snow": 0,
              "vis_km": 10.0,
              "vis_miles": 6.0,
              "gust_mph": 9.2,
              "gust_kph": 14.8,
              "uv": 1.0
            },
          ]
        },
      ]
    },     
  };
  toggleValue:boolean = false;
  constructor(private http: HttpClient) {
    this.apiCall({cityName:'bhubaneswar', pinCode:''});
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

  ngOnInit(): void {
  }

  getFormData(formData: any) {
    console.log("@output works on parent");
    
    console.log(formData);
    this.apiCall(formData);
  }

  menuToggle(val:any) {
    console.log("menu button clicked weather page", val);
    this.toggleValue= val;
  }
}
