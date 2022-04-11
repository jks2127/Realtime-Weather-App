import { Component, Input, OnInit } from '@angular/core';
@Component({
  selector: 'app-hourly',
  templateUrl: './hourly.component.html',
  styleUrls: ['./hourly.component.scss']
})
export class HourlyComponent implements OnInit {
  @Input() weatherData?: any;
  epochNow!: number;
  list:any = [];
  
  constructor() {}

  ngOnInit(): void {
    this.epochNow = (new Date).setMinutes(0,0,0)/1000;
  }

  ngOnChanges() {
    console.log(this.list.length);
    this.list = [];
    console.log(this.weatherData);
    
    for (let i = 0; i < 2; i++) {      
      this.weatherData.forecast.forecastday[i]?.hour.forEach((item: any) => {
        if ((Math.trunc(item.timeEpoch/1000) >= Math.trunc(this.weatherData.current.lastUpdatedEpoch/1000)) && (this.list.length < 12)) {
          this.list.push(item);
        }
      });
    }
  };

  ngAfterViewChecked() {        
    document.getElementById(this.epochNow.toString())?.scrollIntoView({inline: "start", behavior: 'smooth' });
  }
}
