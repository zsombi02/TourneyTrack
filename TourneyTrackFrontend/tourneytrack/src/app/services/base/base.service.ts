import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export abstract class BaseService {

  protected readonly API_ROOT = 'http://localhost:8080';

  constructor() { }
}
