import { EventComment } from './event-comment';
import { User } from './user';
import { Event } from './event';
export class EventPost {
  id: number;
  description: string;
  imageUrl: string;
  createdDate: string;
  lastUpdate: string;
  enabled: boolean;
  rating: string;
  event: Event;
  user: User;
  subject: string;
  comments: EventComment[];

  constructor(
    id?: number,
    description?: string,
    imageUrl?: string,
    createdDate?: string,
    lastUpdate?: string,
    enabled?: boolean,
    rating?: string,
    event?: Event,
    user?: User,
    subject?: string,
    comments?: EventComment[]
  ) {
    this.id = id;
    this.description = description;
    this.imageUrl = imageUrl;
    this.createdDate = createdDate;
    this.lastUpdate = lastUpdate;
    this.enabled = enabled;
    this.rating = rating;
    this.event = event;
    this.user = user;
    this.subject = subject;
    this.comments = comments;
  }
}
