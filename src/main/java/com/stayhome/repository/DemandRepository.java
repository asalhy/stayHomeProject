package com.stayhome.repository;

import com.stayhome.domain.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Demand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandRepository extends JpaRepository<Demand, Long>, JpaSpecificationExecutor<Demand> {

    // Use specification
    // List<Demand> findAllByOrganization(Organization organization);
    // List<Demand> findAllByOrganizationAndAssignee(Organization organization, User assignee);
}
