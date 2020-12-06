import { User } from './user';
import { Address } from './address';
export class Payment {
  id: number;
  cardNumber: String;
  amount: number;
  exp: String;
  createdDate: String;
  lastUpdate: String;
  enabled: boolean;
  address: Address;
  user: User;

constructor(
  id?: number,
  cardNumber?: String,
  amount?: number,
  exp?: String,
  createdDate?: String,
  lastUpdate?: String,
  enabled?: boolean,
  address?: Address,
  user?: User

){
  this.id= id;
  this.cardNumber= cardNumber;
  this.amount= amount;
  this.exp= exp;
  this.createdDate= createdDate;
  this.lastUpdate= lastUpdate;
  this.enabled= enabled;
  this.address= address;
  this.user= user;


}

}
