package com.stayhome.repository;

import com.stayhome.domain.Volunteer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Volunteer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {
}
