export interface SubmitScoreRequest {
  competitionId: number;
  userId?: number;
  ruleId: number;
  description: string;
}
