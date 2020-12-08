import { PrivateEvent } from './../../models/private-event';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { Event } from './../../models/event';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  constructor(private eventSvc: EventService, private pEventSrv: PrivateEventService, private auth: AuthService, private router: Router) { }
  events: Event[];
  pEvents: PrivateEvent[];
  selected: Event = null;
  pSelected: PrivateEvent = null;

  ngOnInit(): void {
    this.loadEvents();
    this.loadPrivateEvents();
  }

  loadEvents(): void {
    this.eventSvc.index().subscribe(
      (data) => {
        this.events = data;
        console.log(this.events);
      },
      (err) => {
        console.error('WorkoutComponent.LoadWorkout(); retrive failed');

      }
    );
  }

  loadPrivateEvents(): void {
    this.pEventSrv.index().subscribe(
      (data) => {
        this.pEvents = data;
        console.log(this.pEvents);
      },
      (err) => {
        console.error('WorkoutComponent.LoadWorkout(); retrive failed');

      }
    );
  }
  eventResult(event){
    this.selected = event;
    localStorage.setItem('event' , "" + this.selected.id);
    this.router.navigateByUrl("/eventDetails");

  }

  pEventResult(pEvent){
    this.selected = pEvent;
    localStorage.setItem('event' , "" + this.selected.id);
    this.router.navigateByUrl("/eventDetails");

  }
//   eventDetails(workout){
// this.selected = workout
//   }

}
