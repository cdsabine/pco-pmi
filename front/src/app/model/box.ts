import {Product} from "./product";

export class Box {
  boxNumber: string;
  quantityActive: number;
  quantityDraft: number;
  quantityTotal: number;
  allActive: boolean;
  products: Product;
}
