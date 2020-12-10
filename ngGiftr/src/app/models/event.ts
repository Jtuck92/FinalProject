import { User } from 'src/app/models/user';
export class Event {
  id: number;
  name: string;
  description: string;
  startDate: string;
  endDate: string;
  enabled: boolean;
  budgetId: number;
  imageUrl: string;
  creatorId: number;
  createdDate: string;
  lastUpdate: string;
  users: User[];

  constructor(
    id?: number,
    name?: string,
    description?: string,
    startDate?: string,
    endDate?: string,
    enabled?: boolean,
    budgetId?: number,
    imageUrl?: string,
    creatorId?: number,
    createdDate?: string,
    lastUpdate?: string,
    users?: User[]
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
    this.enabled = enabled;
    this.budgetId = budgetId;
    this.imageUrl = imageUrl;
    this.creatorId = creatorId;
    this.createdDate = createdDate;
    this.lastUpdate = lastUpdate;
    this.users = users;
  }
}
