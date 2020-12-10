import { Component, OnInit } from '@angular/core';
import { GiftService } from './../../service/gift.service';
import { Gift } from './../../models/gift';
import { UserService } from './../../service/user.service';
import { UserEventsPipe } from './../../pipes/user-events.pipe';
import { PrivateEvent } from './../../models/private-event';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { Event } from './../../models/event';
import { PrivateEventService } from 'src/app/service/private-event.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { relative } from 'path';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  constructor(
    private eventSvc: EventService,
    private router: Router,
    private auth: AuthService
  ) {}
  events: Event[] = [];
  receivers: User[] = [];
  selected: Event = null;

  ngOnInit(): void {
    // this.auth.isHomePageComponent(true);
    this.loadEvents();
  }
  loadEvents(): void {
    this.eventSvc.index().subscribe(
      (data) => {
        this.events = data;
        console.log(this.events);
      },
      (err) => {
        console.error('eventComponent.LoadEvent(); retrive failed');
      }
    );
  }

  eventResult(event) {
    // console.log(event);

    this.selected = event;
    localStorage.setItem('event', '' + this.selected.id);
    this.router.navigateByUrl('/eventDetails');
  }

  findReceiverUsername(index) {
    if (this.receivers[index] == null) {
      return ' Check back Soon! ';
    }
    return this.receivers[index].username;
  }
  findReceiverAddressStreet(index) {
    // console.log(this.receivers[index]);
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.street;
  }
  findReceiverAddressStreet2(index) {
    // console.log([index]);
    if (this.receivers[index] == null) {
      return '';
    }
    return 'Ste/Apt/Unit: ' + this.receivers[index].address.street2;
  }
  findReceiverAddressCity(index) {
    // console.log([index]);
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.city + ', ';
  }
  findReceiverAddressState(index) {
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.stateProvince + ' ';
  }
  findReceiverAddressCountry(index) {
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.country;
  }
  findReceiverAddressZip(index) {
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.zip;
  }
  disableEvent(e) {
    // for (let i = 0; i < this.events.length; i++) {
console.log(e.id);

        this.eventSvc.destroy(e.id).subscribe(
          (data) => {
            console.log(data);

            this.loadEvents();

            location.reload();
          },
          (err) => {
            console.error(' Events disable failed');
          }
        );
      }

  }
// }
