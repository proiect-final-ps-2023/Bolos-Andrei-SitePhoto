export class Photo {
  id!: number;
  name!: string;
  path!: string;
  description!: string;
  userid: number | undefined;
  imagedata!: File;
}
