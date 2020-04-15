package com.stayhome.service.mapper;

import com.stayhome.domain.Demand;
import com.stayhome.service.dto.DemandDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Demand} and its DTO {@link DemandDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocalityMapper.class, OrganizationMapper.class, ServiceTypeMapper.class})
public interface DemandMapper {

    @Mapping(source = "locality.id", target = "localityId")
    @Mapping(source = "assignee.id", target = "assigneeId")
    @Mapping(source = "organization.id", target = "organizationId")
    @Mapping(source = "serviceType.id", target = "serviceTypeId")
    DemandDTO toDto(Demand demand);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(source = "localityId", target = "locality")
    @Mapping(source = "organizationId", target = "organization")
    @Mapping(source = "serviceTypeId", target = "serviceType")
    Demand toEntity(DemandDTO demandDTO);
}
