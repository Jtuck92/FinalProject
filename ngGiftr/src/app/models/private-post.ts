import { PrivateComment } from './private-comment';
import { PrivateEvent } from './private-event';
import { User } from './user';
export class PrivatePost {
id: number;
description: String;
imageUrl: String;
createdDate: String;
lastUpdate: String;
enabled: boolean;
rating: String;
subject: String;
user: User;
prvEvent: PrivateEvent;
prvComments: PrivateComment[];

constructor(
  id?: number,
  description?: String,
  imageUrl?: String,
  createdDate?: String,
  lastUpdate?: String,
  enabled?: boolean,
  rating?: String,
  subject?: String,
  user?: User,
  prvEvent?: PrivateEvent,
  prvComments?: PrivateComment[]


){
  this.id= id;
  this.description= description;
  this.imageUrl= imageUrl;
  this.createdDate= createdDate;
  this.lastUpdate= lastUpdate;
  this.enabled= enabled;
  this.rating= rating;
  this.subject= subject;
  this.user= user;
  this.prvEvent= prvEvent;
  this.prvComments= prvComments;

}


}
