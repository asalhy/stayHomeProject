package com.stayhome.service.mapper;

import com.stayhome.domain.DemandAudit;
import com.stayhome.service.dto.DemandAuditDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link DemandAudit} and its DTO {@link DemandAuditDTO}.
 */
@Mapper(componentModel = "spring")
public interface DemandAuditMapper {

    DemandAuditDTO toDto(DemandAudit entity);
}
