export class Budget {
  id: number;
  lowPrice: number;
  highPrice: number;
  enabled: boolean;
  events: Event [];


  constructor(
    id: number,
    lowPrice: number,
    highPrice: number,
    enabled: boolean,
    events: Event []
  ){
    this.id= id;
    this.lowPrice= lowPrice;
   this.highPrice= highPrice;
    this.enabled= enabled;
    this.events=  events;

  }
}
