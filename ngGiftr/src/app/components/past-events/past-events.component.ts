import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { Event } from './../../models/event';
import { PrivateEventService } from 'src/app/service/private-event.service';

@Component({
  selector: 'app-past-events',
  templateUrl: './past-events.component.html',
  styleUrls: ['./past-events.component.css']
})
export class PastEventsComponent implements OnInit {

  constructor(private eventSvc: EventService, private pEventSrv: PrivateEventService, private auth: AuthService, private router: Router) { }

  events: Event[] = [];
  selected: Event = new Event();

  ngOnInit(): void {
    window.scrollTo(0,0);
    this.auth.isHomePageComponent(true);
    this.loadEvents();
  }
  loadEvents(): void {
    this.eventSvc.index().subscribe(
      (data) => {
        this.events = data;
        // console.log(this.events);
      },
      (err) => {
        console.error('PastEventsComponent.LoadEvents(); retrieve failed');

      }
    );
  }
  eventResult(event){
    this.selected = event;
    localStorage.setItem('event' , "" + this.selected.id);
    this.router.navigateByUrl("/eventDetails");
  }
}
