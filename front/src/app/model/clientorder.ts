import {Product} from "./product";
import {Client} from "./client";

export class Clientorder {
  clientOrderCode: any;
  dateOfOrder: string;
  shipped: boolean;
  totalOrderValue: number;
  productList: Product;
  client: Client
}
