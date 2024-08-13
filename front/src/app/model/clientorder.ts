import {Product} from "./product";

export class Clientorder {
  clientOrderCode: any;
  dateOfOrder: string;
  shipped: boolean;
  totalOrderValue: number;
  productList: Product;
}
