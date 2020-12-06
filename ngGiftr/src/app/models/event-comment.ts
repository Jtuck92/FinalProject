import { User } from './user';
import { EventPost } from './event-post';
export class EventComment {
  id: number;
  comment: String;
  createdDate: String;
  lastUpdate: String;
  enabled: boolean;
  post: EventPost;
  user: User;

constructor(
  id?: number,
  comment?: String,
  createdDate?: String,
  lastUpdate?: String,
  enabled?: boolean,
  post?: EventPost,
  user?: User

){
  this.id= id;
  this.comment=   comment ;
  this.createdDate=   createdDate
  this.lastUpdate=   lastUpdate ;
  this.enabled=   enabled
  this.post=   post ;
  this.user=   user ;

}

}
