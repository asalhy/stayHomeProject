package com.stayhome.repository;

import com.stayhome.domain.Demand;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Demand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandRepository extends JpaRepository<Demand, Long> {

    @Query("select demand from Demand demand where demand.assignee.login = ?#{principal.username}")
    List<Demand> findByAssigneeIsCurrentUser();
}
