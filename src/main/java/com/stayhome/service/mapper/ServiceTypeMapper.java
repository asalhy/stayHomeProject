package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.ServiceTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceType} and its DTO {@link ServiceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {VolunteerMapper.class})
public interface ServiceTypeMapper extends EntityMapper<ServiceTypeDTO, ServiceType> {

    @Mapping(source = "volunteer.id", target = "volunteerId")
    ServiceTypeDTO toDto(ServiceType serviceType);

    @Mapping(source = "volunteerId", target = "volunteer")
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
