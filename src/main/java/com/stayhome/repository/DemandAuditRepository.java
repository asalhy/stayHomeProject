package com.stayhome.repository;

import com.stayhome.domain.DemandAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DemandAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandAuditRepository extends JpaRepository<DemandAudit, Long> {
}
