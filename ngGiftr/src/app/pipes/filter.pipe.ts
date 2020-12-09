import { SearchResultsComponent } from './../components/search-results/search-results.component';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(items: any[], field: string, field2: string, value: string): any[] {
    var results = [];
    var results1 = [];
    var results2 = [];
    if (!items) {
      return [];
    }
    if (!field || !value) {
      return items;
    }
results1 = items.filter((singleItem) =>
singleItem[field].toLowerCase().includes(value.toLowerCase())
);
results2 = items.filter((singleItem) =>
singleItem[field2].toLowerCase().includes(value.toLowerCase())
);

results1.forEach(r => {
  results.push(r);
});

results2.forEach(r => {
  results.push(r);

});


    return results;
  }
}
