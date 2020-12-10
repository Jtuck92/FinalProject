import { User } from './user';
import { Event } from './event';
import { EventComment } from './event-comment';
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
  comments: EventComment[];
  subject: string;

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
    comments?: EventComment[],
    subject?: string
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
    this.comments = comments;
    this.subject = subject;
  }
}
