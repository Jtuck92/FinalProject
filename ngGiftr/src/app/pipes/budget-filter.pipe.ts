import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'budgetFilter'
})
export class BudgetFilterPipe implements PipeTransform {

  transform(events: any [], filterType: number): any [] {
    const results = [];
    if (filterType == 0) {
      return events;
    }
        for (let i = 0; i < events.length; i++){
          if(events[i].budget.id == filterType) {
            results.push(events[i]);
          }
        }
        return results;
      }
}
