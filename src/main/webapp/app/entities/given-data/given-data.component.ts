import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IGivenData } from 'app/shared/model/given-data.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { GivenDataService } from './given-data.service';
import dayGridPlugin from '@fullcalendar/daygrid';

@Component({
  selector: 'jhi-given-data',
  styleUrls: ['./given-data.scss'],
  templateUrl: './given-data.component.html'
})
export class GivenDataComponent implements OnInit, OnDestroy {
  currentAccount: any;
  givenData: IGivenData[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;
  postalCode: string;
  calendarPlugins = [dayGridPlugin]; // important!
  calendarEvents = [
    { title: 'event 1', date: '2019-04-01' }
  ];

  constructor(
    protected givenDataService: GivenDataService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected accountService: AccountService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager
  ) {
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.routeData = this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.previousPage = data.pagingParams.page;
      this.reverse = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.postalCode = '';
    });
  }

  addEvents() {
    this.calendarEvents = this.calendarEvents.concat( // creates a new array!
      { title: 'event 2', date: '2019-04-02' }
    );
  }

  modifyTitle(eventIndex, newTitle) {
    let calendarEvents = this.calendarEvents.slice(); // a clone
    let singleEvent = Object.assign({}, calendarEvents[eventIndex]); // a clone
    singleEvent.title = newTitle;
    calendarEvents[eventIndex] = singleEvent;
    this.calendarEvents = calendarEvents; // reassign the array
  }

  loadAll() {
    this.givenDataService
      .query({
        page: this.page - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IGivenData[]>) => this.paginateGivenData(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  loadPage(page: number) {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  transition() {
    this.router.navigate(['/given-data'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    });
    this.loadAll();
  }

  clear() {
    this.page = 0;
    this.router.navigate([
      '/given-data',
      {
        page: this.page,
        sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
      }
    ]);
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInGivenData();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IGivenData) {
    return item.id;
  }

  registerChangeInGivenData() {
    this.eventSubscriber = this.eventManager.subscribe('givenDataListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateGivenData(data: IGivenData[], headers: HttpHeaders) {
    if (headers !== null) {
      this.links = this.parseLinks.parse(headers.get('link'));
      this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    }
    this.givenData = data;
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  onSearch() {
    console.log(this.postalCode);
    this.searchByPostalCode(this.postalCode);
  }

  searchByPostalCode(postalCode) {
    this.givenDataService
      .findByPostalCode(postalCode)
      .subscribe(
        // (res) => console.log(res),
        (res: any) => this.paginateGivenData(res.body, null),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

}
