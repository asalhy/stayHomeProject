package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.ServiceTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceType} and its DTO {@link ServiceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ServiceTypeMapper extends EntityMapper<ServiceTypeDTO, ServiceType> {


    @Mapping(target = "organizations", ignore = true)
    @Mapping(target = "removeOrganization", ignore = true)
    ServiceType toEntity(ServiceTypeDTO serviceTypeDTO);

    default ServiceType fromId(Long id) {
        if (id == null) {
            return null;
        }
        ServiceType serviceType = new ServiceType();
        serviceType.setId(id);
        return serviceType;
    }
}
