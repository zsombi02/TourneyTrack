import { UserDto } from './user.dto';
import { RuleSetDto } from './rule-set.dto';

export interface CompetitionDto {
  id: number;
  name: string;
  description: string;
  gameMaster: UserDto;
  participants: UserDto[];
  deadline: string;       // ISO dátum string
  inProgress: boolean;
  ruleSets: RuleSetDto[];
}
