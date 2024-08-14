import {Country} from "./country";
import {Clientorder} from "./clientorder";

export class Client {
  userCode: number;
  appUsername: string;
  emailAddress: string;
  address: string;
  countryCode: number;
  country: Country;
  repeatedTransactions: number;
  totalValueAchieved: number;
  clientOrderList: Clientorder;
}
