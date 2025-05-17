export interface CreateCompetitionRequest {
  name: string;
  description: string;
  deadline: string;      // ISO dátum string
  gameMasterId: number;
  ruleSetIds: number[];
}
