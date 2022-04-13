import { Component, Input, OnInit } from '@angular/core';
@Component({
  selector: 'app-hourly',
  templateUrl: './hourly.component.html',
  styleUrls: ['./hourly.component.scss']
})
export class HourlyComponent implements OnInit {
  @Input() weatherData?: any;
  epochNow!: number;
  list: Array<any> = new Array({
    code: 0,
    icon: "",
    text: ""
  });
  
  constructor() {}

  ngOnInit(): void {
    this.epochNow = (new Date).setMinutes(0,0,0)/1000;
  }

  ngOnChanges() {
    this.getHourlyData();
  };

  getHourlyData() {
    this.list = [];
    for (let i = 0; i < 2; i++) {      
      this.weatherData.forecast.forecastday[i]?.hour.forEach((item: any) => {
        if ((item.tempC!=0) && (Math.trunc(item.timeEpoch/1000) >= Math.trunc(this.weatherData.current.lastUpdatedEpoch/1000)) && (this.list.length < 12)) {
          this.list.push(item);          
        }
      });
    }
  }
}
