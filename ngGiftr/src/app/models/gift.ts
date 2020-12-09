import { User } from 'src/app/models/user';
import { Event } from 'src/app/models/event';
export class Gift {
  id: number;
  price: number;
  weight: number;
  description: String;
  enabled: boolean;
  event: Event;
  gifter: User;
  receiver: User;
  rating: number;
  name: string;
  imageUrl: string;

  constructor(
    id?: number,
    price?: number,
    weight?: number,
    description?: string,
    enabled?: boolean,
    event?: Event,
    gifter?: User,
    receiver?: User,
    rating?: number,
    name?: string,
    imageUrl?: string
  ) {
    this.id = id;
    this.price = price;
    this.weight = weight;
    this.description = description;
    this.enabled = enabled;
    this.event = event;
    this.gifter = gifter;
    this.receiver = receiver;
    this.rating = rating;
    this.name = name;
    this.imageUrl = imageUrl;
  }
}
