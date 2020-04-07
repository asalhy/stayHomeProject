package com.stayhome.repository;

import com.stayhome.domain.Delegation;
import com.stayhome.domain.Governorate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Delegation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DelegationRepository extends JpaRepository<Delegation, Long> {

    List<Delegation> findByGovernorate(Governorate governorate);
}
