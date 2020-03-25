package com.stayhome.repository;

import com.stayhome.domain.Gouvernorat;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Gouvernorat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GouvernoratRepository extends JpaRepository<Gouvernorat, Long> {
}
