package com.stayhome.repository;

import com.stayhome.domain.Municipality;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Municipality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
}
