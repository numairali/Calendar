package com.numair.web.rest;

import com.numair.domain.GivenData;
import com.numair.repository.GivenDataRepository;
import com.numair.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.numair.domain.GivenData}.
 */
@RestController
@RequestMapping("/api")
public class GivenDataResource {

    private final Logger log = LoggerFactory.getLogger(GivenDataResource.class);

    private static final String ENTITY_NAME = "givenData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GivenDataRepository givenDataRepository;

    public GivenDataResource(GivenDataRepository givenDataRepository) {
        this.givenDataRepository = givenDataRepository;
    }

    /**
     * {@code POST  /given-data} : Create a new givenData.
     *
     * @param givenData the givenData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
     *         body the new givenData, or with status {@code 400 (Bad Request)} if
     *         the givenData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/given-data")
    public ResponseEntity<GivenData> createGivenData(@Valid @RequestBody GivenData givenData)
            throws URISyntaxException {
        log.debug("REST request to save GivenData : {}", givenData);
        if (givenData.getId() != null) {
            throw new BadRequestAlertException("A new givenData cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GivenData result = givenDataRepository.save(givenData);
        return ResponseEntity
                .created(new URI("/api/given-data/" + result.getId())).headers(HeaderUtil
                        .createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /given-data} : Updates an existing givenData.
     *
     * @param givenData the givenData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the updated givenData, or with status {@code 400 (Bad Request)} if
     *         the givenData is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the givenData couldn't be
     *         updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/given-data")
    public ResponseEntity<GivenData> updateGivenData(@Valid @RequestBody GivenData givenData)
            throws URISyntaxException {
        log.debug("REST request to update GivenData : {}", givenData);
        if (givenData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GivenData result = givenDataRepository.save(givenData);
        return ResponseEntity.ok().headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, givenData.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /given-data} : get all the givenData.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
     *         of givenData in body.
     */
    @GetMapping("/given-data")
    public ResponseEntity<List<GivenData>> getAllGivenData(Pageable pageable,
            @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of GivenData");
        log.info(queryParams.toString());
        Page<GivenData> page = givenDataRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<GivenData>> getBrand(@RequestParam(value = "postalCode") String postalCode) {
        List<GivenData> page = givenDataRepository.findGivenDataByPostalCode(postalCode);
       // HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(null).body(page);
    }

    /**
     * {@code GET  /given-data/:id} : get the "id" givenData.
     *
     * @param id the id of the givenData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the givenData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/given-data/{id}")
    public ResponseEntity<GivenData> getGivenData(@PathVariable Long id) {
        log.debug("REST request to get GivenData : {}", id);
        Optional<GivenData> givenData = givenDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(givenData);
    }

    /**
     * {@code DELETE  /given-data/:id} : delete the "id" givenData.
     *
     * @param id the id of the givenData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/given-data/{id}")
    public ResponseEntity<Void> deleteGivenData(@PathVariable Long id) {
        log.debug("REST request to delete GivenData : {}", id);
        givenDataRepository.deleteById(id);
        return ResponseEntity.noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
                .build();
    }
}
