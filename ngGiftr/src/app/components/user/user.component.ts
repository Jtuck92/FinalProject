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
import { relative } from 'path';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  constructor(
    private eventSvc: EventService,
    private pEventSrv: PrivateEventService,
    private auth: AuthService,
    private giftSrv: GiftService,
    private router: Router,
    private uEPipe: UserEventsPipe,
    private userSrv: UserService
  ) {}
  events: Event[];
  pEvents: PrivateEvent[];
  selected: Event = null;
  pSelected: PrivateEvent = null;
  stringId = '';
  numUserId = 0;
  user: User = null;
  gifts: Gift[] = [];
  userEvents: Event[];
  receivers: User[] = [];
  activeGifts: Gift[] = [];
  userGifts: Gift[] = [];

  ngOnInit(): void {
    this.loadPrivateEvents();
    this.loadGifts();
  }

  loadPersonalEventList() {
    this.stringId = localStorage.getItem('userId');
    // console.log(this.activeGifts);

    this.numUserId = parseInt(this.stringId);
    this.userSrv.show(this.numUserId).subscribe(
      (data) => {
        this.user = data;
        for (let i = 0; i < this.activeGifts.length; i++) {
          if (this.activeGifts[i].gifter.id == this.user.id) {
            this.receivers.push(this.activeGifts[i].receiver);
          }
        }
        this.events = this.uEPipe.transform(this.activeGifts, this.user);
      },
      (err) => {
        console.error('User retrive failed');
      }
    );
  }

  removeEvent(e) {
    for (let i = 0; i < this.gifts.length; i++) {
      if (this.gifts[i].gifter.id == this.user.id) {
        if (this.gifts[i].event.id == e.id) {
          this.giftSrv.destroy(this.gifts[i].id).subscribe(
            (data) => {
              console.log(this.gifts);
              this.loadGifts();
              location.reload();
            },
            (err) => {
              console.error('Private Events retrive failed');
            }
          );
        }
      }
    }
  }

  findNote(e, index) {
    this.userGifts = [];
    for (let i = 0; i < this.gifts.length; i++) {
      if (this.gifts[i].gifter.id == this.user.id) {
        if (e.id == this.gifts[i].event.id) {
          // console.log("event matched");

          this.userGifts.push(this.gifts[i]);
          // console.log("user matched");
        }
        // console.log(this.userGifts);
      }
    }
    let receivers = [];
    let notes = [];
    for (let j = 0; j < this.userGifts.length; j++) {
      receivers.push(this.userGifts[j].receiver);
    }
              // console.log(receivers);
    // for (let i = 0; i < this.userGifts.length; i++) {
    //   for (let j = 0; j < receivers.length; j++) {
    //     if(receivers[j] == null){
    //       notes.push("");
    //     }
    //     else if (this.gifts[i].gifter.id == receivers[j].id) {
    //       console.log('receiver matched');

    //       notes.push(receivers[j].note);
    //     }
    //     console.log(notes);
    //   }
    // }
    //   findReceiverNote(this.gifts[i].id);
    // }

    //           // for (let j = 0; j < this.gifts.length; j++) {
    //             //   if (this.gifts[j].gifter.id == receiver.id) {
    //               //     if (this.gifts[i].event.id == e.id) {
    //                 //       return this.gifts[j].note;
    //                 //     }
    //                 //   }
    //                 // }
    //                 console.log(notes);

    //                 return notes[index];
    //               }

    return '';
  }

  loadGifts(): void {
    this.giftSrv.index().subscribe(
      (data) => {
        this.gifts = data;
        for (let i = 0; i < this.gifts.length; i++) {
          console.log(this.gifts[i].enabled);

          if (this.gifts[i].enabled) {
            this.activeGifts.push(this.gifts[i]);
          }
        }
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
  eventResult(event) {
    // console.log(event);

    this.selected = event;
    localStorage.setItem('event', '' + this.selected.id);
    this.router.navigateByUrl('/eventDetails');
  }

  pEventResult() {
    this.router.navigateByUrl('/gallery');
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
  //   eventDetails(workout){
  // this.selected = workout
  //   }
}
