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
  notes: String [] = [];
  receiverGifts: Gift [] = [];
  loadRecieverCount = 0;

  ngOnInit(): void {
    if (!localStorage.getItem('foo')) {
      console.log("Setting Foo");

      localStorage.setItem('foo', 'no reload')
      location.reload()
    } else {
      localStorage.removeItem('foo')
      this.loadPrivateEvents();
      this.loadGifts();
    }

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
            // if(this.activeGifts[i].receiver == null){
            //   this.receivers.push(new User());
            // }
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


  loadGifts(): void {
    this.giftSrv.index().subscribe(
      (data) => {
        this.gifts = data;
        for (let i = 0; i < this.gifts.length; i++) {

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

  displayNote(event, index){
    for(let i = 0; i < this.gifts.length; i++){
      if(this.receivers[index] == null){
        return "";
      }
      if( this.gifts[i].gifter.id == this.receivers[index].id){
      if(this.gifts[i].event.id == event.id){
            if(!this.receiverGifts.includes(this.gifts[i])){
              this.receiverGifts.push(this.gifts[i])
          }
        }
      }
    }
  if(this.receiverGifts[index].note == null){
  return "No notes from receipient"
  }
  return this.receiverGifts[index].note
  }

}
