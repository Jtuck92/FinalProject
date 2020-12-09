import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'userPrvEvents'
})
export class UserPrvEventsPipe implements PipeTransform {

  transform(value: unknown, ...args: unknown[]): unknown {
    return null;
  }

}
