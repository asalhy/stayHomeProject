package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.LocalityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Locality} and its DTO {@link LocalityDTO}.
 */
@Mapper(componentModel = "spring", uses = {DelegationMapper.class})
public interface LocalityMapper extends EntityMapper<LocalityDTO, Locality> {

    @Mapping(source = "delegation.id", target = "delegationId")
    LocalityDTO toDto(Locality locality);

    @Mapping(source = "delegationId", target = "delegation")
    Locality toEntity(LocalityDTO localityDTO);

    default Locality fromId(Long id) {
        if (id == null) {
            return null;
        }
        Locality locality = new Locality();
        locality.setId(id);
        return locality;
    }
}
