import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGivenData } from 'app/shared/model/given-data.model';

type EntityResponseType = HttpResponse<IGivenData>;
type EntityArrayResponseType = HttpResponse<IGivenData[]>;

@Injectable({ providedIn: 'root' })
export class GivenDataService {
  public resourceUrl = SERVER_API_URL + 'api/given-data';

  constructor(protected http: HttpClient) { }

  create(givenData: IGivenData): Observable<EntityResponseType> {
    return this.http.post<IGivenData>(this.resourceUrl, givenData, { observe: 'response' });
  }

  update(givenData: IGivenData): Observable<EntityResponseType> {
    return this.http.put<IGivenData>(this.resourceUrl, givenData, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGivenData>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByPostalCode(postalCode: string): Observable<EntityResponseType> {
    console.log(postalCode);
    return this.http.get<IGivenData>('http://localhost:9000/api?postalCode=' + postalCode, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGivenData[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
