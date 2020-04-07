package com.stayhome.repository;

import com.stayhome.domain.Delegation;
import com.stayhome.domain.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Locality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {

    List<Locality> findByDelegation(Delegation delegation);
}
