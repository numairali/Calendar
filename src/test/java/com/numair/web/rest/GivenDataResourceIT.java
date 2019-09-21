package com.numair.web.rest;

import com.numair.ShyftBaseApp;
import com.numair.domain.GivenData;
import com.numair.repository.GivenDataRepository;
import com.numair.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.numair.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link GivenDataResource} REST controller.
 */
@SpringBootTest(classes = ShyftBaseApp.class)
public class GivenDataResourceIT {

    private static final String DEFAULT_POSTAL_CODE = "AAAAAA";
    private static final String UPDATED_POSTAL_CODE = "BBBBBB";

    private static final String DEFAULT_CITY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CITY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROVINCE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROVINCE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ZONE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZONE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUNDAY = 1;
    private static final Integer UPDATED_SUNDAY = 2;

    private static final Integer DEFAULT_MONDAY = 1;
    private static final Integer UPDATED_MONDAY = 2;

    private static final Integer DEFAULT_TUESDAY = 1;
    private static final Integer UPDATED_TUESDAY = 2;

    private static final Integer DEFAULT_WEDNESDAY = 1;
    private static final Integer UPDATED_WEDNESDAY = 2;

    private static final Integer DEFAULT_THURSDAY = 1;
    private static final Integer UPDATED_THURSDAY = 2;

    private static final Integer DEFAULT_FRIDAY = 1;
    private static final Integer UPDATED_FRIDAY = 2;

    private static final Integer DEFAULT_SATURDAY = 1;
    private static final Integer UPDATED_SATURDAY = 2;

    @Autowired
    private GivenDataRepository givenDataRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restGivenDataMockMvc;

    private GivenData givenData;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GivenDataResource givenDataResource = new GivenDataResource(givenDataRepository);
        this.restGivenDataMockMvc = MockMvcBuilders.standaloneSetup(givenDataResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GivenData createEntity(EntityManager em) {
        GivenData givenData = new GivenData()
            .postalCode(DEFAULT_POSTAL_CODE)
            .cityCode(DEFAULT_CITY_CODE)
            .cityName(DEFAULT_CITY_NAME)
            .provinceCode(DEFAULT_PROVINCE_CODE)
            .zoneCode(DEFAULT_ZONE_CODE)
            .companyName(DEFAULT_COMPANY_NAME)
            .sunday(DEFAULT_SUNDAY)
            .monday(DEFAULT_MONDAY)
            .tuesday(DEFAULT_TUESDAY)
            .wednesday(DEFAULT_WEDNESDAY)
            .thursday(DEFAULT_THURSDAY)
            .friday(DEFAULT_FRIDAY)
            .saturday(DEFAULT_SATURDAY);
        return givenData;
    }

    @BeforeEach
    public void initTest() {
        givenData = createEntity(em);
    }

    @Test
    @Transactional
    public void createGivenData() throws Exception {
        int databaseSizeBeforeCreate = givenDataRepository.findAll().size();

        // Create the GivenData
        restGivenDataMockMvc.perform(post("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isCreated());

        // Validate the GivenData in the database
        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeCreate + 1);
        GivenData testGivenData = givenDataList.get(givenDataList.size() - 1);
        assertThat(testGivenData.getPostalCode()).isEqualTo(DEFAULT_POSTAL_CODE);
        assertThat(testGivenData.getCityCode()).isEqualTo(DEFAULT_CITY_CODE);
        assertThat(testGivenData.getCityName()).isEqualTo(DEFAULT_CITY_NAME);
        assertThat(testGivenData.getProvinceCode()).isEqualTo(DEFAULT_PROVINCE_CODE);
        assertThat(testGivenData.getZoneCode()).isEqualTo(DEFAULT_ZONE_CODE);
        assertThat(testGivenData.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testGivenData.getSunday()).isEqualTo(DEFAULT_SUNDAY);
        assertThat(testGivenData.getMonday()).isEqualTo(DEFAULT_MONDAY);
        assertThat(testGivenData.getTuesday()).isEqualTo(DEFAULT_TUESDAY);
        assertThat(testGivenData.getWednesday()).isEqualTo(DEFAULT_WEDNESDAY);
        assertThat(testGivenData.getThursday()).isEqualTo(DEFAULT_THURSDAY);
        assertThat(testGivenData.getFriday()).isEqualTo(DEFAULT_FRIDAY);
        assertThat(testGivenData.getSaturday()).isEqualTo(DEFAULT_SATURDAY);
    }

    @Test
    @Transactional
    public void createGivenDataWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = givenDataRepository.findAll().size();

        // Create the GivenData with an existing ID
        givenData.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGivenDataMockMvc.perform(post("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isBadRequest());

        // Validate the GivenData in the database
        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPostalCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = givenDataRepository.findAll().size();
        // set the field null
        givenData.setPostalCode(null);

        // Create the GivenData, which fails.

        restGivenDataMockMvc.perform(post("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isBadRequest());

        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = givenDataRepository.findAll().size();
        // set the field null
        givenData.setCityCode(null);

        // Create the GivenData, which fails.

        restGivenDataMockMvc.perform(post("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isBadRequest());

        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = givenDataRepository.findAll().size();
        // set the field null
        givenData.setCityName(null);

        // Create the GivenData, which fails.

        restGivenDataMockMvc.perform(post("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isBadRequest());

        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProvinceCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = givenDataRepository.findAll().size();
        // set the field null
        givenData.setProvinceCode(null);

        // Create the GivenData, which fails.

        restGivenDataMockMvc.perform(post("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isBadRequest());

        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkZoneCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = givenDataRepository.findAll().size();
        // set the field null
        givenData.setZoneCode(null);

        // Create the GivenData, which fails.

        restGivenDataMockMvc.perform(post("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isBadRequest());

        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCompanyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = givenDataRepository.findAll().size();
        // set the field null
        givenData.setCompanyName(null);

        // Create the GivenData, which fails.

        restGivenDataMockMvc.perform(post("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isBadRequest());

        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGivenData() throws Exception {
        // Initialize the database
        givenDataRepository.saveAndFlush(givenData);

        // Get all the givenDataList
        restGivenDataMockMvc.perform(get("/api/given-data?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(givenData.getId().intValue())))
            .andExpect(jsonPath("$.[*].postalCode").value(hasItem(DEFAULT_POSTAL_CODE.toString())))
            .andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE.toString())))
            .andExpect(jsonPath("$.[*].cityName").value(hasItem(DEFAULT_CITY_NAME.toString())))
            .andExpect(jsonPath("$.[*].provinceCode").value(hasItem(DEFAULT_PROVINCE_CODE.toString())))
            .andExpect(jsonPath("$.[*].zoneCode").value(hasItem(DEFAULT_ZONE_CODE.toString())))
            .andExpect(jsonPath("$.[*].companyName").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
            .andExpect(jsonPath("$.[*].sunday").value(hasItem(DEFAULT_SUNDAY)))
            .andExpect(jsonPath("$.[*].monday").value(hasItem(DEFAULT_MONDAY)))
            .andExpect(jsonPath("$.[*].tuesday").value(hasItem(DEFAULT_TUESDAY)))
            .andExpect(jsonPath("$.[*].wednesday").value(hasItem(DEFAULT_WEDNESDAY)))
            .andExpect(jsonPath("$.[*].thursday").value(hasItem(DEFAULT_THURSDAY)))
            .andExpect(jsonPath("$.[*].friday").value(hasItem(DEFAULT_FRIDAY)))
            .andExpect(jsonPath("$.[*].saturday").value(hasItem(DEFAULT_SATURDAY)));
    }
    
    @Test
    @Transactional
    public void getGivenData() throws Exception {
        // Initialize the database
        givenDataRepository.saveAndFlush(givenData);

        // Get the givenData
        restGivenDataMockMvc.perform(get("/api/given-data/{id}", givenData.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(givenData.getId().intValue()))
            .andExpect(jsonPath("$.postalCode").value(DEFAULT_POSTAL_CODE.toString()))
            .andExpect(jsonPath("$.cityCode").value(DEFAULT_CITY_CODE.toString()))
            .andExpect(jsonPath("$.cityName").value(DEFAULT_CITY_NAME.toString()))
            .andExpect(jsonPath("$.provinceCode").value(DEFAULT_PROVINCE_CODE.toString()))
            .andExpect(jsonPath("$.zoneCode").value(DEFAULT_ZONE_CODE.toString()))
            .andExpect(jsonPath("$.companyName").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.sunday").value(DEFAULT_SUNDAY))
            .andExpect(jsonPath("$.monday").value(DEFAULT_MONDAY))
            .andExpect(jsonPath("$.tuesday").value(DEFAULT_TUESDAY))
            .andExpect(jsonPath("$.wednesday").value(DEFAULT_WEDNESDAY))
            .andExpect(jsonPath("$.thursday").value(DEFAULT_THURSDAY))
            .andExpect(jsonPath("$.friday").value(DEFAULT_FRIDAY))
            .andExpect(jsonPath("$.saturday").value(DEFAULT_SATURDAY));
    }

    @Test
    @Transactional
    public void getNonExistingGivenData() throws Exception {
        // Get the givenData
        restGivenDataMockMvc.perform(get("/api/given-data/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGivenData() throws Exception {
        // Initialize the database
        givenDataRepository.saveAndFlush(givenData);

        int databaseSizeBeforeUpdate = givenDataRepository.findAll().size();

        // Update the givenData
        GivenData updatedGivenData = givenDataRepository.findById(givenData.getId()).get();
        // Disconnect from session so that the updates on updatedGivenData are not directly saved in db
        em.detach(updatedGivenData);
        updatedGivenData
            .postalCode(UPDATED_POSTAL_CODE)
            .cityCode(UPDATED_CITY_CODE)
            .cityName(UPDATED_CITY_NAME)
            .provinceCode(UPDATED_PROVINCE_CODE)
            .zoneCode(UPDATED_ZONE_CODE)
            .companyName(UPDATED_COMPANY_NAME)
            .sunday(UPDATED_SUNDAY)
            .monday(UPDATED_MONDAY)
            .tuesday(UPDATED_TUESDAY)
            .wednesday(UPDATED_WEDNESDAY)
            .thursday(UPDATED_THURSDAY)
            .friday(UPDATED_FRIDAY)
            .saturday(UPDATED_SATURDAY);

        restGivenDataMockMvc.perform(put("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGivenData)))
            .andExpect(status().isOk());

        // Validate the GivenData in the database
        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeUpdate);
        GivenData testGivenData = givenDataList.get(givenDataList.size() - 1);
        assertThat(testGivenData.getPostalCode()).isEqualTo(UPDATED_POSTAL_CODE);
        assertThat(testGivenData.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testGivenData.getCityName()).isEqualTo(UPDATED_CITY_NAME);
        assertThat(testGivenData.getProvinceCode()).isEqualTo(UPDATED_PROVINCE_CODE);
        assertThat(testGivenData.getZoneCode()).isEqualTo(UPDATED_ZONE_CODE);
        assertThat(testGivenData.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testGivenData.getSunday()).isEqualTo(UPDATED_SUNDAY);
        assertThat(testGivenData.getMonday()).isEqualTo(UPDATED_MONDAY);
        assertThat(testGivenData.getTuesday()).isEqualTo(UPDATED_TUESDAY);
        assertThat(testGivenData.getWednesday()).isEqualTo(UPDATED_WEDNESDAY);
        assertThat(testGivenData.getThursday()).isEqualTo(UPDATED_THURSDAY);
        assertThat(testGivenData.getFriday()).isEqualTo(UPDATED_FRIDAY);
        assertThat(testGivenData.getSaturday()).isEqualTo(UPDATED_SATURDAY);
    }

    @Test
    @Transactional
    public void updateNonExistingGivenData() throws Exception {
        int databaseSizeBeforeUpdate = givenDataRepository.findAll().size();

        // Create the GivenData

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGivenDataMockMvc.perform(put("/api/given-data")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(givenData)))
            .andExpect(status().isBadRequest());

        // Validate the GivenData in the database
        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGivenData() throws Exception {
        // Initialize the database
        givenDataRepository.saveAndFlush(givenData);

        int databaseSizeBeforeDelete = givenDataRepository.findAll().size();

        // Delete the givenData
        restGivenDataMockMvc.perform(delete("/api/given-data/{id}", givenData.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<GivenData> givenDataList = givenDataRepository.findAll();
        assertThat(givenDataList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GivenData.class);
        GivenData givenData1 = new GivenData();
        givenData1.setId(1L);
        GivenData givenData2 = new GivenData();
        givenData2.setId(givenData1.getId());
        assertThat(givenData1).isEqualTo(givenData2);
        givenData2.setId(2L);
        assertThat(givenData1).isNotEqualTo(givenData2);
        givenData1.setId(null);
        assertThat(givenData1).isNotEqualTo(givenData2);
    }
}
