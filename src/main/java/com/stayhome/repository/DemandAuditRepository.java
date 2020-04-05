package com.stayhome.repository;

import com.stayhome.domain.DemandAudit;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the DemandAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DemandAuditRepository extends JpaRepository<DemandAudit, Long> {

    @Query("select demandAudit from DemandAudit demandAudit where demandAudit.user.login = ?#{principal.username}")
    List<DemandAudit> findByUserIsCurrentUser();
}
