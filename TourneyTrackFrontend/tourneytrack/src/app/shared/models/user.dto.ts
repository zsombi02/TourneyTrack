import { UserTypeDto } from './user-type.enum';

export interface UserDto {
  id: number;
  name: string;
  email: string;
  password?: string; // csak regisztrációnál
  type: UserTypeDto | string;
}
