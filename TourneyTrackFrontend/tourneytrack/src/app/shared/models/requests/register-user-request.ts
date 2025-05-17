import { UserTypeDto } from '../user-type.enum';

export interface RegisterUserRequest {
  name: string;
  email: string;
  password: string;
  type: UserTypeDto;
}
