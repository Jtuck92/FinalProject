import { Address } from './address';
export class User {
  id: number;
  username: string;
  email: string;
  password: string;
  enabled: boolean;
  firstName: string;
  lastName: string;
  createdDate: string;
  lastUpdate: string;
  birthDate: string;
  gender: string;
  role: string;
  address: Address;

  constructor(
    id?: number,
    username?: string,
    email?: string,
    password?: string,
    enabled?: boolean,
    firstName?: string,
    lastName?: string,
    createdDate?: string,
    lastUpdate?: string,
    birthDate?: string,
    gender?: string,
    role?: string,
    address?: Address
  ) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
    this.enabled = enabled;
    this.firstName = firstName;
    this.lastName = lastName;
    this.createdDate = createdDate;
    this.lastUpdate = lastUpdate;
    this.birthDate = birthDate;
    this.gender = gender;
    this.role = role;
    this.address = address;
  }
}
