import { RuleDto } from './rule.dto';

export interface RuleSetDto {
  id: number;
  name: string;
  rules: RuleDto[];
}
