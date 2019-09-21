import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IGivenData, GivenData } from 'app/shared/model/given-data.model';
import { GivenDataService } from './given-data.service';

@Component({
  selector: 'jhi-given-data-update',
  templateUrl: './given-data-update.component.html'
})
export class GivenDataUpdateComponent implements OnInit {
  givenData: IGivenData;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    postalCode: [null, [Validators.required, Validators.minLength(6), Validators.maxLength(6)]],
    cityCode: [null, [Validators.required]],
    cityName: [null, [Validators.required]],
    provinceCode: [null, [Validators.required]],
    zoneCode: [null, [Validators.required]],
    companyName: [null, [Validators.required]],
    sunday: [],
    monday: [],
    tuesday: [],
    wednesday: [],
    thursday: [],
    friday: [],
    saturday: []
  });

  constructor(protected givenDataService: GivenDataService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ givenData }) => {
      this.updateForm(givenData);
      this.givenData = givenData;
    });
  }

  updateForm(givenData: IGivenData) {
    this.editForm.patchValue({
      id: givenData.id,
      postalCode: givenData.postalCode,
      cityCode: givenData.cityCode,
      cityName: givenData.cityName,
      provinceCode: givenData.provinceCode,
      zoneCode: givenData.zoneCode,
      companyName: givenData.companyName,
      sunday: givenData.sunday,
      monday: givenData.monday,
      tuesday: givenData.tuesday,
      wednesday: givenData.wednesday,
      thursday: givenData.thursday,
      friday: givenData.friday,
      saturday: givenData.saturday
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const givenData = this.createFromForm();
    if (givenData.id !== undefined) {
      this.subscribeToSaveResponse(this.givenDataService.update(givenData));
    } else {
      this.subscribeToSaveResponse(this.givenDataService.create(givenData));
    }
  }

  private createFromForm(): IGivenData {
    const entity = {
      ...new GivenData(),
      id: this.editForm.get(['id']).value,
      postalCode: this.editForm.get(['postalCode']).value,
      cityCode: this.editForm.get(['cityCode']).value,
      cityName: this.editForm.get(['cityName']).value,
      provinceCode: this.editForm.get(['provinceCode']).value,
      zoneCode: this.editForm.get(['zoneCode']).value,
      companyName: this.editForm.get(['companyName']).value,
      sunday: this.editForm.get(['sunday']).value,
      monday: this.editForm.get(['monday']).value,
      tuesday: this.editForm.get(['tuesday']).value,
      wednesday: this.editForm.get(['wednesday']).value,
      thursday: this.editForm.get(['thursday']).value,
      friday: this.editForm.get(['friday']).value,
      saturday: this.editForm.get(['saturday']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGivenData>>) {
    result.subscribe((res: HttpResponse<IGivenData>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
