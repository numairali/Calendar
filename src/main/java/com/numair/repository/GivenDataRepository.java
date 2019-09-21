package com.numair.repository;

import com.numair.domain.GivenData;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;


/**
 * Spring Data  repository for the GivenData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GivenDataRepository extends JpaRepository<GivenData, Long> {

    public List<GivenData> findGivenDataByPostalCode(String postalCode);
}
