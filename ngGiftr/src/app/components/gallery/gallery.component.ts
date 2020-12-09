import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  constructor(private userSvc: UserService, private eventSvc: EventService, private eventPostSvc: EventPostService, private router: Router) { }
    users: User[] = [];
    selectedUser: User = null;
    events: Event[] = [];
    selectedEvent: Event = null;
    eventPosts: EventPost[] = [];
    selectedEventPost: Event = null;

  ngOnInit(): void {
    this.loadUsers();
    this.loadEvents();
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
  loadEvents(): void {
    this.eventSvc.index().subscribe(
      (data) => {
        this.events = data;
        console.log(this.events);
      },
      (err) => {
        console.error('EventComponent.LoadEvent(); retrieve failed');
      }
    );
  }
  eventResult(event){
    this.selectedEvent = event;
    localStorage.setItem('event', "" + this.selectedEvent);
    this.router.navigateByUrl("/events");
  }
  loadEventPosts(): void {
    this.eventPostSvc.index().subscribe(
      (data) => {
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
    localStorage.setItem('eventPost', "" + this.selectedEventPost);
    this.router.navigateByUrl("/eventPosts");
  }
}
