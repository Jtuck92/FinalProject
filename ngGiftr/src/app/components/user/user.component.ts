import { GiftService } from './../../service/gift.service';
import { Gift } from './../../models/gift';
import { UserService } from './../../service/user.service';
import { UserEventsPipe } from './../../pipes/user-events.pipe';
import { PrivateEvent } from './../../models/private-event';
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { Event } from './../../models/event';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  constructor(private eventSvc: EventService, private pEventSrv: PrivateEventService, private auth: AuthService, private giftSrv: GiftService, private router: Router, private uEPipe: UserEventsPipe, private userSrv: UserService) { }
  events: Event[];
  pEvents: PrivateEvent[];
  selected: Event = null;
  pSelected: PrivateEvent = null;
  stringId = "";
  numUserId = 0;
  user: User = null;
  gifts: Gift [];

  ngOnInit(): void {
    this.loadPrivateEvents();
    this.loadGifts();
  }

  loadPersonalEventList(){
    this.stringId = localStorage.getItem("userId");

    this.numUserId = parseInt(this.stringId);
    this.userSrv.show(this.numUserId).subscribe(
      (data) => {
        this.user = data;
        this.events = this.uEPipe.transform(this.gifts, this.user);
      },
      (err) => {
        console.error('User retrive failed');

      }
      );

    }

    loadGifts(): void {
      this.giftSrv.index().subscribe(
        (data) => {
          this.gifts = data;
          this.loadPersonalEventList();
      },
      (err) => {
        console.error('Gifts retrive failed');

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
        console.error('Private Events retrive failed');

      }
    );
  }
  eventResult(event){
    this.selected = event;
    localStorage.setItem('event' , "" + this.selected.id);
    this.router.navigateByUrl("/eventDetails");

  }

  pEventResult(){
    this.router.navigateByUrl("/gallery");

  }
//   eventDetails(workout){
// this.selected = workout
//   }

}
