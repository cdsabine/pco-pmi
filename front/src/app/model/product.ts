export class Product {
  sku: string;
  title: string;
  price: number;
  activeProduct: boolean;
  colour: string;
  size: string;
  quantity: number;
  prodCondition: string;
  vendorCode: string;

  constructor() {
    this.sku = "sku";
    this.title = "Title";
    this.price = -1;
    this.activeProduct = false;
    this.colour = "Colour";
    this.size = "Size";
    this.quantity = -1;
    this.prodCondition = "Condition";
    this.vendorCode = "Vendor";
  }
}
