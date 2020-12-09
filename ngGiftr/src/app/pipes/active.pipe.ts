import { Pipe, PipeTransform } from '@angular/core';
import { Event } from './../models/event';
@Pipe({
  name: 'active'
})
export class ActivePipe implements PipeTransform {

  transform(fullList: any []): any[] {
    const results = []
   for (let i = 0; i< fullList.length; i++){
     if(fullList[i].enabled) {
       results.push(fullList[i])
   }
    }

    return results;
  }

}
