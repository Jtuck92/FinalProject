import { EventComment } from './../../models/event-comment';
import { EventCommentService } from './../../service/event-comment.service';
import { EventPostService } from 'src/app/service/event-post.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/service/auth.service';
import { EventService } from 'src/app/service/event.service';
import { EventPost } from 'src/app/models/event-post';

@Component({
  selector: 'app-message-board',
  templateUrl: './message-board.component.html',
  styleUrls: ['./message-board.component.css'],
})
export class MessageBoardComponent implements OnInit {
  constructor(
    private eventSvc: EventService,
    private eventPostSvc: EventPostService,
    private auth: AuthService,
    private router: Router,
    private eventCommentSvc: EventCommentService
  ) {}
  comSelected: EventComment = new EventComment();
  events: EventPost[];
  selected: EventPost = new EventPost();
  idString = null;
  loggedIn = false;
  newComment = new EventComment();

  ngOnInit(): void {
    if (this.auth.checkLogin) {
      this.loggedIn = true;
    }
    this.idString = localStorage.getItem('eventPost');
    console.log(this.idString);
    this.idString = parseInt(this.idString);
    this.eventPostSvc.show(this.idString).subscribe(
      (data) => {
        console.log(data);
        this.selected = data;
        console.log(this.selected);
      },
      (err) => {
        this.router.navigateByUrl('notFound');
      }
    );
  }
  catch(error) {
    this.router.navigateByUrl('notFound');
  }
  pEventSignup() {
    localStorage.setItem('eventPost', '' + this.selected.id);
  }

  postComment() {
    if(!this.auth.checkLogin()) {
      this.router.navigateByUrl('login')
    }
    this.newComment.post = this.selected;
    this.eventCommentSvc.create(this.newComment).subscribe(
      (good) => {
        this.selected.comments.push(good);
        location.reload();
      },
      (bad) => {
        console.error(bad);
      }
    );
    this.newComment = new EventComment();
  }


}
