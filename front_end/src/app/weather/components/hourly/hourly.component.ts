import { Component, Input, OnInit, ViewChild } from '@angular/core';
@Component({
  selector: 'app-hourly',
  templateUrl: './hourly.component.html',
  styleUrls: ['./hourly.component.scss']
})
export class HourlyComponent implements OnInit {
  @Input() weatherData?: any;
  // @ViewChild("jk") MyProp: any;
  epochNow!: number;
  
  constructor() {}

  ngOnInit(): void {
    this.epochNow = (new Date).setMinutes(0,0,0)/1000;
  }

  ngAfterViewChecked() {        
    document.getElementById(this.epochNow.toString())?.scrollIntoView({inline: "start", behavior: 'smooth' });
  }
}
