import { PrivatePost } from './private-post';
import { User } from './user';

export class PrivateComment {
  id: number;
  comment: string;
  createdDate: string;
  lastUpdate: string;
  user: User;
  post: PrivatePost;
  enabled: boolean;

  constructor(
    id?: number,
    comment?: string,
    createdDate?: string,
    lastUpdate?: string,
    user?: User,
    post?: PrivatePost,
    enabled?: boolean
  ) {
    this.id = id;
    this.comment = comment;
    this.createdDate = createdDate;
    this.lastUpdate = lastUpdate;
    this.user = user;
    this.post = post;
    this.enabled = enabled;
  }
}
