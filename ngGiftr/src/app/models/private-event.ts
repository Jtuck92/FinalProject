import { PrivatePost } from './private-post';
import { User } from './user';
export class PrivateEvent {
  id: number;
  name: String;
  createdDate: String;
  lastUpdate: String;
  enabled: boolean;
  imageUrl: String;
  description: String;
  privateEventManager: User;
  posts: PrivatePost[];
  privateGroupUsers: User[];


  constructor(
    id?: number,
    name?: String,
    createdDate?: String,
    lastUpdate?: String,
    enabled?: boolean,
    imageUrl?: String,
    description?: String,
    privateEventManager?: User,
    posts?: PrivatePost[],
    privateGroupUsers?: User[]


  ){
    this.id= id;
    this.name= name;
    this.createdDate= createdDate;
    this.lastUpdate= lastUpdate;
    this.enabled= enabled;
    this.imageUrl= imageUrl;
    this.description= description;
    this.privateEventManager=privateEventManager ;
    this.posts= posts;
    this.privateGroupUsers= privateGroupUsers;


  }
}
