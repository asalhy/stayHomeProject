package com.stayhome.repository;

import com.stayhome.domain.Delegation;
import com.stayhome.domain.Municipality;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Municipality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {

    List<Municipality> findAllByDelegation(Delegation delegation);
}
