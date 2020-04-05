package com.stayhome.service.mapper;

import com.stayhome.domain.ServiceType;
import com.stayhome.repository.ServiceTypeRepository;
import com.stayhome.service.dto.ServiceTypeDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper for the entity {@link ServiceType} and its DTO {@link ServiceTypeDTO}.
 */
@Mapper(componentModel = "spring")
public abstract class ServiceTypeMapper {

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    public abstract ServiceType toEntity(ServiceTypeDTO serviceType);

    public abstract ServiceTypeDTO toDto(ServiceType serviceType);

    public ServiceType findById(Long id) {
        return this.serviceTypeRepository.getOne(id);
    }
}
