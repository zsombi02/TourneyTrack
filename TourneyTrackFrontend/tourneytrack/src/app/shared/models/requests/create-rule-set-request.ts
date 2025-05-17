import { CreateRuleRequest } from './create-rule-request';

export interface CreateRuleSetRequest {
  name: string;
  rules: CreateRuleRequest[];
}
