package com.stayhome.service.mapper;

import com.stayhome.domain.*;
import com.stayhome.service.dto.ServiceTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceType} and its DTO {@link ServiceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {VolunteerMapper.class})
public interface ServiceTypeMapper extends EntityMapper<ServiceTypeDTO, ServiceType> {

    ServiceTypeDTO toDto(ServiceType serviceType);

    ServiceType toEntity(ServiceTypeDTO serviceTypeDTO);
}
