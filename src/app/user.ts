import { Photo } from "./photo"
export class User {
  id!: number;
  name!: string;
  email!: string;
  password!: string;
  admin!: boolean;
  listaPhoto!: Photo[];
  isloggedin!: boolean;
}
