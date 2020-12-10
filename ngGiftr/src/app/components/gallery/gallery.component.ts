import { AuthService } from 'src/app/service/auth.service';
import { UserEventsPipe } from './../../pipes/user-events.pipe';
import { GiftService } from './../../service/gift.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Event } from './../../models/event';
import { EventPostService } from 'src/app/service/event-post.service';
import { EventService } from 'src/app/service/event.service';
import { UserService } from 'src/app/service/user.service';
import { EventPost } from './../../models/event-post';
import { User } from './../../models/user';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
  stringId: string;
  numUserId: number;
  userSrv: any;
  user: any;
  gifts: any;
  receivers: any;
  uEPipe: any;
  giftSrv: any;

  constructor(private userSvc: UserService,
    private eventSvc: EventService,
    private eventPostSvc: EventPostService,
    private giftSvc: GiftService,
    private userEventsPipe: UserEventsPipe,
    private auth: AuthService,
    private router: Router) { }

    users: User[] = [];
    selectedUser: User = null;
    events: Event[] = [];
    selectedEvent: Event = null;
    eventPosts: EventPost[] = [];
    selectedEventPost: Event = null;


    newImageUrl: string = "";

  ngOnInit(): void {
    this.auth.isHomePageComponent(true);
    // this.loadUsers();
    // this.loadGifts();
    this.loadEventPosts();
  }
  loadUsers(): void {
    this.userSvc.index().subscribe(
      (data) => {
        this.users = data;
        console.log(this.users);
      },
      (err) => {
        console.error('UserComponent.LoadUser(); retrieve failed');
      }
    );
  }
  userResult(user){
    this.selectedUser = user;
    localStorage.setItem('user', "" + this.selectedUser.id);
    this.router.navigateByUrl("/users");
  }
  // loadPersonalEventList() {
  //   this.stringId = localStorage.getItem('userId');

  //   this.numUserId = parseInt(this.stringId);
  //   this.userSvc.show(this.numUserId).subscribe(
  //     (data) => {
  //       this.user = data;
  //       for (let i = 0; i < this.gifts.length; i++) {
  //         if (this.gifts[i].gifter.id == this.user.id) {
  //           this.receivers.push(this.gifts[i].receiver);
  //         }
  //       }
  //       this.events = this.uEPipe.transform(this.gifts, this.user);
  //     },
  //     (err) => {
  //       console.error('User retrive failed');
  //     }
    // );
  // }
  // loadGifts(): void {
  //   this.giftSvc.index().subscribe(
  //     (data) => {
  //       this.gifts = data;
  //       this.loadPersonalEventList();
  //     },
  //     (err) => {
  //       console.error('Gifts retrive failed');
  //     }
  //   );
  // }
  // eventResult(event){
  //   this.selectedEvent = event;
  //   localStorage.setItem('event', "" + this.selectedEvent);
  //   this.router.navigateByUrl("/events");
  // }
  loadEventPosts(): void {
    this.eventPostSvc.index().subscribe(
      (data) => {
        console.log(data);
        this.eventPosts = data;
        console.log(this.eventPosts);
      },
      (err) => {
        console.error('EventPostComponent.LoadEventPost(); retrieve failed');
      }
    );
  }
  eventPostResult(eventPost){
    this.selectedEventPost = eventPost;
    localStorage.setItem('eventPost', "" + this.selectedEventPost.id);
    this.router.navigateByUrl("/post/:id");
  }
  addImageUrl(url: string){
  // TODO
  }
}
