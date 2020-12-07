import { AuthService } from './../../service/auth.service';
import { PrivateEvent } from './../../models/private-event';
import { Event } from './../../models/event';
import { EventService } from './../../service/event.service';
import { Component, OnInit } from '@angular/core';
import { error } from 'protractor';
import { PrivateEventService } from 'src/app/service/private-event.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private eventSvc: EventService, private pEventSrv: PrivateEventService, private auth: AuthService) { }
events: Event[];
pEvents: PrivateEvent[];
  ngOnInit(): void {
    this.auth.isHomePageComponent(true);
    this.loadEvents();
  }
  loadEvents(): void {
    this.pEventSrv.index().subscribe(
      (data) => {
        this.pEvents = data;
        console.log(this.events);
      },
      (err) => {
        console.error('WorkoutComponent.LoadWorkout(); retrive failed');

      }
    );
  }

}
