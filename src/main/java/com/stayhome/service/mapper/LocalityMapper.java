package com.stayhome.service.mapper;

import com.stayhome.domain.Locality;
import com.stayhome.repository.LocalityRepository;
import com.stayhome.service.dto.LocalityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapper for the entity {@link Locality} and its DTO {@link LocalityDTO}.
 */
@Mapper(componentModel = "spring")
public abstract class LocalityMapper {

    @Autowired
    private LocalityRepository localityRepository;

    @Mapping(source = "delegation.id", target = "delegationId")
    public abstract LocalityDTO toDto(Locality locality);

    public Locality findById(Long id) {
        return this.localityRepository.getOne(id);
    }
}
