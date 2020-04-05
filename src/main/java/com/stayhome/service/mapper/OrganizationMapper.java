package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.OrganizationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organization} and its DTO {@link OrganizationDTO}.
 */
@Mapper(componentModel = "spring", uses = {ServiceTypeMapper.class})
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {


    @Mapping(target = "removeServiceTypes", ignore = true)

    default Organization fromId(Long id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }
}
