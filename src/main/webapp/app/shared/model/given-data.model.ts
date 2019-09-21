export interface IGivenData {
  id?: number;
  postalCode?: string;
  cityCode?: string;
  cityName?: string;
  provinceCode?: string;
  zoneCode?: string;
  companyName?: string;
  sunday?: number;
  monday?: number;
  tuesday?: number;
  wednesday?: number;
  thursday?: number;
  friday?: number;
  saturday?: number;
}

export class GivenData implements IGivenData {
  constructor(
    public id?: number,
    public postalCode?: string,
    public cityCode?: string,
    public cityName?: string,
    public provinceCode?: string,
    public zoneCode?: string,
    public companyName?: string,
    public sunday?: number,
    public monday?: number,
    public tuesday?: number,
    public wednesday?: number,
    public thursday?: number,
    public friday?: number,
    public saturday?: number
  ) {}
}
