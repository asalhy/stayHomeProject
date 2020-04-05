package com.stayhome.service.mapper;

import com.stayhome.domain.Organization;
import com.stayhome.repository.OrganizationRepository;
import com.stayhome.service.dto.OrganizationDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper for the entity {@link Organization} and its DTO {@link OrganizationDTO}.
 */
@Mapper(componentModel = "spring", uses = ServiceTypeMapper.class)
public abstract class OrganizationMapper {

    @Autowired
    private OrganizationRepository organizationRepository;

    public abstract OrganizationDTO toDto(Organization organization);

    public Organization findById(Long id) {
        return this.organizationRepository.getOne(id);
    }
}
