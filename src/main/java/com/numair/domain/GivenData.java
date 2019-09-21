package com.numair.domain;



import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A GivenData.
 */
@Entity
@Table(name = "given_data")
public class GivenData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 6, max = 6)
    @Column(name = "postal_code", length = 6, nullable = false)
    private String postalCode;

    @NotNull
    @Column(name = "city_code", nullable = false)
    private String cityCode;

    @NotNull
    @Column(name = "city_name", nullable = false)
    private String cityName;

    @NotNull
    @Column(name = "province_code", nullable = false)
    private String provinceCode;

    @NotNull
    @Column(name = "zone_code", nullable = false)
    private String zoneCode;

    @NotNull
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "sunday")
    private Integer sunday;

    @Column(name = "monday")
    private Integer monday;

    @Column(name = "tuesday")
    private Integer tuesday;

    @Column(name = "wednesday")
    private Integer wednesday;

    @Column(name = "thursday")
    private Integer thursday;

    @Column(name = "friday")
    private Integer friday;

    @Column(name = "saturday")
    private Integer saturday;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public GivenData postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public GivenData cityCode(String cityCode) {
        this.cityCode = cityCode;
        return this;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public GivenData cityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public GivenData provinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
        return this;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public GivenData zoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
        return this;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public GivenData companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getSunday() {
        return sunday;
    }

    public GivenData sunday(Integer sunday) {
        this.sunday = sunday;
        return this;
    }

    public void setSunday(Integer sunday) {
        this.sunday = sunday;
    }

    public Integer getMonday() {
        return monday;
    }

    public GivenData monday(Integer monday) {
        this.monday = monday;
        return this;
    }

    public void setMonday(Integer monday) {
        this.monday = monday;
    }

    public Integer getTuesday() {
        return tuesday;
    }

    public GivenData tuesday(Integer tuesday) {
        this.tuesday = tuesday;
        return this;
    }

    public void setTuesday(Integer tuesday) {
        this.tuesday = tuesday;
    }

    public Integer getWednesday() {
        return wednesday;
    }

    public GivenData wednesday(Integer wednesday) {
        this.wednesday = wednesday;
        return this;
    }

    public void setWednesday(Integer wednesday) {
        this.wednesday = wednesday;
    }

    public Integer getThursday() {
        return thursday;
    }

    public GivenData thursday(Integer thursday) {
        this.thursday = thursday;
        return this;
    }

    public void setThursday(Integer thursday) {
        this.thursday = thursday;
    }

    public Integer getFriday() {
        return friday;
    }

    public GivenData friday(Integer friday) {
        this.friday = friday;
        return this;
    }

    public void setFriday(Integer friday) {
        this.friday = friday;
    }

    public Integer getSaturday() {
        return saturday;
    }

    public GivenData saturday(Integer saturday) {
        this.saturday = saturday;
        return this;
    }

    public void setSaturday(Integer saturday) {
        this.saturday = saturday;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GivenData)) {
            return false;
        }
        return id != null && id.equals(((GivenData) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GivenData{" +
            "id=" + getId() +
            ", postalCode='" + getPostalCode() + "'" +
            ", cityCode='" + getCityCode() + "'" +
            ", cityName='" + getCityName() + "'" +
            ", provinceCode='" + getProvinceCode() + "'" +
            ", zoneCode='" + getZoneCode() + "'" +
            ", companyName='" + getCompanyName() + "'" +
            ", sunday=" + getSunday() +
            ", monday=" + getMonday() +
            ", tuesday=" + getTuesday() +
            ", wednesday=" + getWednesday() +
            ", thursday=" + getThursday() +
            ", friday=" + getFriday() +
            ", saturday=" + getSaturday() +
            "}";
    }
}
