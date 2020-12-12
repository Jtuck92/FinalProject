export class Budget {
  id: number;
  lowPrice: number;
  highPrice: number;
  enabled: boolean;
  events: Event [];
  name: String;


  constructor(
    id?: number,
    lowPrice?: number,
    highPrice?: number,
    enabled?: boolean,
    events?: Event [],
    name?: String
  ){
    this.id= id;
    this.lowPrice= lowPrice;
   this.highPrice= highPrice;
    this.enabled= enabled;
    this.events=  events;
    this.name = name

  }
}
