import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGivenData } from 'app/shared/model/given-data.model';

@Component({
  selector: 'jhi-given-data-detail',
  templateUrl: './given-data-detail.component.html'
})
export class GivenDataDetailComponent implements OnInit {
  givenData: IGivenData;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ givenData }) => {
      this.givenData = givenData;
    });
  }

  previousState() {
    window.history.back();
  }
}
