import { User } from 'src/app/models/user';
import { Gift } from 'src/app/models/gift';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'userEvents'
})
export class UserEventsPipe implements PipeTransform {

  transform(giftList: any[], user: User ): any[] {
const results = [];
for (let i = 0; i < giftList.length; i++){
if(giftList[i].gifter.id == user.id){
  results.push(giftList[i].event);
}
}
    return results;
  }

}
