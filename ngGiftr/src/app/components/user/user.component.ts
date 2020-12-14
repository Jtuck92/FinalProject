import { Address } from './../../models/address';
import { AddressService } from 'src/app/service/address.service';
import { EventPostService } from './../../service/event-post.service';
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
import { EventPost } from 'src/app/models/event-post';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css'],
})
export class UserComponent implements OnInit {
  constructor(
    private authService: AuthService,
    private eventSvc: EventService,
    private pEventSrv: PrivateEventService,
    private auth: AuthService,
    private giftSrv: GiftService,
    private router: Router,
    private uEPipe: UserEventsPipe,
    private userSrv: UserService,
    private eventPostSvc: EventPostService,
    private addressSvc: AddressService
  ) {}
  eventPost = new EventPost();
  events: Event[];
  pEvents: PrivateEvent[];
  selected: Event = null;
  pSelected: PrivateEvent = null;
  stringId = '';
  addressId = 0;
  numUserId = 0;
  user: User = null;
  gifts: Gift[] = [];
  userEvents: Event[];
  receivers: User[] = [];
  activeGifts: Gift[] = [];
  userGifts: Gift[] = [];
  notes: String[] = [];
  receiverGifts: Gift[] = [];
  loadRecieverCount = 0;
  updateGift = new Gift();
  updateUser = new User();
  updateAddress = new Address();
  pageView = 'Profile';
  pageViews = [
    'Update User Profile',
    'Update Address',
    'Profile'

  ];

  ngOnInit(): void {
    this.auth.isHomePageComponent(false);
    if (!localStorage.getItem('foo')) {
      // console.log("Setting Foo");

      localStorage.setItem('foo', 'no reload');
      location.reload();
    } else {
      localStorage.removeItem('foo');
      this.loadPrivateEvents();
      this.loadGifts();
    }
    window.scrollTo(0,0);
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
            this.userGifts.push(this.activeGifts[i]);
            // if(this.activeGifts[i].receiver == null){
            //   this.receivers.push(new User());
            // }
            this.receivers.push(this.activeGifts[i].receiver);
          }
        }
        this.events = this.uEPipe.transform(this.activeGifts, this.user);
      },
      (err) => {
        console.error('User retrieve failed');
      }
    );
  }

  setUpdateGift(event) {
    for (let i = 0; i < this.userGifts.length; i++) {
      //  console.log("in for loop");

      if (this.userGifts[i].event.id == event.id) {
        this.updateGift = this.userGifts[i];
        //  console.log(this.updateGift);
      }
    }
  }

  checkGiftDetails(event) {
    for (let i = 0; i < this.userGifts.length; i++) {
      if (this.userGifts[i].event.id == event.id) {
        if (this.userGifts[i].description != undefined) {
          return true;
        }
      }
    }
    return false;
  }

  submitGiftUpdate() {
    this.giftSrv.update(this.updateGift).subscribe(
      (data) => {
        // console.log(this.gifts);
        this.loadGifts();
        this.updateGift = new Gift();
        location.reload();
      },
      (err) => {
        console.error('Private Events retrieve failed');
      }
    );
  }

  removeEvent(e) {
    for (let i = 0; i < this.gifts.length; i++) {
      if (this.gifts[i].gifter.id == this.user.id) {
        if (this.gifts[i].event.id == e.id) {
          this.giftSrv.destroy(this.gifts[i].id).subscribe(
            (data) => {
              // console.log(this.gifts);
              this.loadGifts();
              location.reload();
            },
            (err) => {
              console.error('Private Events retrieve failed');
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
        console.error('Gifts retrieve failed');
      }
    );
  }

  isEventEnabled(event) {
    if (event.enabled) {
      return 'eventEnabled';
    }
    return 'eventDisabled';
  }

  loadPrivateEvents(): void {
    this.pEventSrv.index().subscribe(
      (data) => {
        console.log(data);

        // this.pEvents = data;
      },
      (err) => {
        console.error('Private Events retrieve failed');
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
    return this.receivers[index].address.street2;
  }
  findReceiverAddressCity(index) {
    // console.log([index]);
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.city + ',';
  }
  findReceiverAddressState(index) {
    if (this.receivers[index] == null) {
      return '';
    }
    return this.receivers[index].address.stateProvince;
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

  displayNote(event, index) {
    for (let i = 0; i < this.gifts.length; i++) {
      if (this.receivers[index] == null) {
        return '';
      }
      if (this.gifts[i].gifter.id == this.receivers[index].id) {
        if (this.gifts[i].event.id == event.id) {
          if (!this.receiverGifts.includes(this.gifts[i])) {
            this.receiverGifts.push(this.gifts[i]);
          }
        }
      }
    }
    if (this.receiverGifts[index].note == null) {
      return 'No notes from receipient';
    }
    return this.receiverGifts[index].note;
  }

  submitEvent() {}
  // Update Address IN DB ====================================

  changeAddress() {
    this.addressSvc.update(this.updateAddress).subscribe(
      (data) => {
        // console.log(Event);
        localStorage.removeItem('pageView');
        localStorage.setItem('pageView', 'Content');
        localStorage.removeItem('listType');
        localStorage.setItem('listType', 'profile');
        this.updateAddress = new Address();
        location.reload();
      },
      (err) => {
        console.error('User LoadUser(); retrieve failed');
      }
    );
  }

  // Update User IN DB ====================================

  changeUserProfile() {
    this.userSrv.update(this.updateUser).subscribe(
      (data) => {
        this.user = data;
        console.log(this.user);

        // console.log(Event);
        this.authService.logout();
        this.authService.login(this.updateUser.username, this.updateUser.password).subscribe(
          (data) => {
            console.log(this.updateUser);

            // this.user = data;
            localStorage.removeItem('pageView');
            localStorage.setItem('pageView', 'Content');
            localStorage.removeItem('listType');
            localStorage.setItem('listType', 'profile');
            this.updateUser = new User();

            // console.log(localStorage.getItem("userId"));
            // if( this.user.username == 'giftr'){
            //   this.router.navigateByUrl('/admin');
            // }else{
              // this.router.navigateByUrl('/profile');
              location.reload();

          },
          (err) => {
            console.error('error on Login*******');
          }
        );

      },
      (err) => {
        console.error('User LoadUser(); retrieve failed');
      }

      );
    // console.log(user);
  }
  // START NON CREATE CLICK EVENTS ===================================================

  confirmCancelProfile(form: NgForm) {
    let response = confirm(
      'Are you sure you want to leave? Any changes will be lost'
    );
    if (response) {
      localStorage.removeItem('pageView');
      localStorage.setItem('pageView', 'profile');
      form.reset();
      location.reload();
    }
  }
  // START CLICK EVENTS ===================================================
  findUser() {
    this.stringId = localStorage.getItem('userId');
    this.numUserId = parseInt(this.stringId);
    this.userSrv.show(this.numUserId).subscribe(
      (data) => {
        this.updateUser = data;
        console.log(this.updateUser);
      },
      (err) => {
        console.error('User ShowUser(); retrieve failed');
      }
    );
  }

  findAddress() {
    // this.stringId = localStorage.getItem('userId');
    // this.numUserId = parseInt(this.stringId);
    this.addressId = this.user.address.id
    this.addressSvc.show(this.addressId).subscribe(
      (data) => {
        this.updateAddress = data;
        console.log(this.updateAddress);
      },
      (err) => {
        console.error('User ShowUser(); retrieve failed');
      }
    );
  }
}
