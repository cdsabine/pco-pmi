import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterPipeButtonProduct'
})
export class FilterPipeButtonProductPipe implements PipeTransform {
  private resolveKeyPath(obj: any, keyPath: string): any {
    return keyPath.split('.').reduce((acc, key) => acc && acc[key], obj);
  }

  transform(value: any[], key: string): any[] {
    if (!value || !key) return [];
    const uniqueValues = Array.from(new Set(value.map(item => this.resolveKeyPath(item, key))));
    return uniqueValues.map(val => value.find(item => this.resolveKeyPath(item, key) === val));
  }
}
