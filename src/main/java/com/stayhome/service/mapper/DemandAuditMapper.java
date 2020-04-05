package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.DemandAuditDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DemandAudit} and its DTO {@link DemandAuditDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, DemandMapper.class})
public interface DemandAuditMapper extends EntityMapper<DemandAuditDTO, DemandAudit> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "demand.id", target = "demandId")
    DemandAuditDTO toDto(DemandAudit demandAudit);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "demandId", target = "demand")
    DemandAudit toEntity(DemandAuditDTO demandAuditDTO);

    default DemandAudit fromId(Long id) {
        if (id == null) {
            return null;
        }
        DemandAudit demandAudit = new DemandAudit();
        demandAudit.setId(id);
        return demandAudit;
    }
}
