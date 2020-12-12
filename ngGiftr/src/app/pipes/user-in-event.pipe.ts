import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'userInEvent'
})
export class UserInEventPipe implements PipeTransform {

  transform(userList: any[], userId: number): boolean {
    for(let i = 0; i < userList.length; i++) {
      if(userList[i].id == userId) {
        return true;
      }
    }
    return false;
  }

}
