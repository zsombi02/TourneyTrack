import { UserDto } from './user.dto';
import { RuleDto } from './rule.dto';

export interface ScoreEntryDto {
  player: UserDto;
  rule: RuleDto;
  pointsEarned: number;
  competitionId: number;
}
