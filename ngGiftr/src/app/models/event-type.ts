export class EventType {
  id: number;
  name: String;
  enabled: boolean;
  description: String;
  imageUrl: String;
  events: Event[];


  constructor(
    id?: number,
    name?: String,
    enabled?: boolean,
    description?: String,
    imageUrl?: String,
    events?: Event[]

  ){

    this.id= id;
    this.name= name;
    this.enabled=enabled ;
    this.description= description;
    this.imageUrl= imageUrl;
    this.events= events;
  }
}
