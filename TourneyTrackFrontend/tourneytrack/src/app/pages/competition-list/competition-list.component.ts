import {Component, OnInit} from '@angular/core';
import {CompetitionPublicService} from '../../services/competition/competition-public.service';
import {CompetitionDto} from '../../shared/models/competition.dto';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatExpansionModule} from '@angular/material/expansion';

@Component({
  selector: 'app-competition-list',
  imports: [CommonModule, MatCardModule, MatButtonModule, MatExpansionModule],
  templateUrl: './competition-list.component.html',
  styleUrl: './competition-list.component.css'
})
export class CompetitionListComponent implements OnInit {
  competitions: CompetitionDto[] = [];
  loading = true;

  constructor(
    private competitionService: CompetitionPublicService,
    private router: Router
  ) {}

  ngOnInit() {
    this.competitionService.getAllCompetitions().subscribe({
      next: data => {
        this.competitions = data;
        this.loading = false;
      },
      error: err => {
        // TODO: Hiba kezelése
        this.loading = false;
      }
    });
  }

  toggleExpand(c: any) {
    c._expanded = !c._expanded;
  }

  openDetails(id: number) {
    this.router.navigate(['/competitions', id]);
  }
}
