package com.stayhome.repository;

import com.stayhome.domain.Governorate;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Governorate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GovernorateRepository extends JpaRepository<Governorate, Long> {
}
