import {UserDto} from './user.dto';

export interface ScoreboardPlayer {
  player: UserDto;
  total: number;
  rulePoints: { [ruleId: number]: number };
  expanded?: boolean;
}
