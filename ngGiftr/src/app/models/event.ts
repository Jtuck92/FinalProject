import { Budget } from 'src/app/models/budget';
import { User } from 'src/app/models/user';
export class Event {
  id: number;
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  enabled: boolean;
  budget: Budget;
  imageUrl: string;
  eventManager: User;
  createdDate: string;
  lastUpdate: string;
  users: User[];
  region: number;

  constructor(
    id?: number,
    name?: string,
    description?: string,
    startDate?: string,
    endDate?: string,
    enabled?: boolean,
    budget?: Budget,
    imageUrl?: string,
    eventManager?: User,
    createdDate?: string,
    lastUpdate?: string,
    users?: User[],
    region?: number
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.enabled = enabled;
    this.budget = budget;
    this.imageUrl = imageUrl;
    this.eventManager = eventManager;
    this.createdDate = createdDate;
    this.lastUpdate = lastUpdate;
    this.users = users;
    this.region = region;
  }
}
