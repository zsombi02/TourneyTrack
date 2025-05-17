import { UserDto } from './user.dto';
import { RuleDto } from './rule.dto';

export interface SubmissionDto {
  id: number;
  competitionId: number;
  user: UserDto;
  rule: RuleDto;
  submittedAt: string;   // ISO dátum string
  description: string;
  status: string;
  reviewerComment: string;
}
