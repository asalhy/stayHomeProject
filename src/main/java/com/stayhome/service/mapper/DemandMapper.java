package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.DemandDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Demand} and its DTO {@link DemandDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocalityMapper.class, UserMapper.class, OrganizationMapper.class, ServiceTypeMapper.class})
public interface DemandMapper extends EntityMapper<DemandDTO, Demand> {

    @Mapping(source = "locality.id", target = "localityId")
    @Mapping(source = "assignee.id", target = "assigneeId")
    @Mapping(source = "organization.id", target = "organizationId")
    @Mapping(source = "serviceType.id", target = "serviceTypeId")
    DemandDTO toDto(Demand demand);

    @Mapping(target = "demandAudits", ignore = true)
    @Mapping(target = "removeDemandAudit", ignore = true)
    @Mapping(source = "localityId", target = "locality")
    @Mapping(source = "assigneeId", target = "assignee")
    @Mapping(source = "organizationId", target = "organization")
    @Mapping(source = "serviceTypeId", target = "serviceType")
    Demand toEntity(DemandDTO demandDTO);

    default Demand fromId(Long id) {
        if (id == null) {
            return null;
        }
        Demand demand = new Demand();
        demand.setId(id);
        return demand;
    }
}
