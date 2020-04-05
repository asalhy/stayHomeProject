package com.stayhome.repository;

import com.stayhome.domain.Locality;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Locality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {
}
