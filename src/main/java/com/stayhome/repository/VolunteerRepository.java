package com.stayhome.repository;

import com.stayhome.domain.Volunteer;
import com.stayhome.service.dto.VolunteerDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Volunteer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

  @Query("SELECT v FROM Volunteer v WHERE v.municipality.delegation.gouvernorat.id= :id")
	Page<Volunteer> findAllByGouvernorat(@Param("id") Long gouvernoratId, Pageable pageable);

}
