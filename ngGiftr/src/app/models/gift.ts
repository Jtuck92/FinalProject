export class Gift {
  id: number;
  price: number;
  weight: number;
  description: string;
  enabled: boolean;
  eventId: number;
  gifterId: number;
  receiverId: number;
  rating: number;
  name: string;
  imageUrl: string;

  constructor(
    id?: number,
    price?: number,
    weight?: number,
    description?: string,
    enabled?: boolean,
    eventId?: number,
    gifterId?: number,
    receiverId?: number,
    rating?: number,
    name?: string,
    imageUrl?: string
  ) {
    this.id = id;
    this.price = price;
    this.weight = weight;
    this.description = description;
    this.enabled = enabled;
    this.eventId = eventId;
    this.gifterId = gifterId;
    this.receiverId = receiverId;
    this.rating = rating;
    this.name = name;
    this.imageUrl = imageUrl;
  }
}
