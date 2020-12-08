import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Event } from './../../models/event';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css'],
})
export class EventDetailsComponent implements OnInit {
  constructor(
    private eventSvc: EventService,
    private pEventSrv: PrivateEventService,
    private auth: AuthService,
    private router: Router
  ) {}
  events: Event[];
  selected: Event = null;
  idString = null;
  loggedIn = false;

  ngOnInit(): void {

    if(this.auth.checkLogin){
      this.loggedIn = true;
    }
    console.log(this.selected);

    this.idString = localStorage.getItem('event');
    this.idString = parseInt(this.idString);
    this.eventSvc.show(this.idString).subscribe(
      (data) => {
        this.selected = data;
        console.log(this.selected);
        localStorage.removeItem('event');

      },
      (err) => {
        this.router.navigateByUrl('notFound');
      }
    );

  }
  catch(error) {
    this.router.navigateByUrl('notFound');
  }

}
