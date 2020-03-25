package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.MunicipalityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Municipality} and its DTO {@link MunicipalityDTO}.
 */
@Mapper(componentModel = "spring", uses = {DelegationMapper.class})
public interface MunicipalityMapper extends EntityMapper<MunicipalityDTO, Municipality> {

    @Mapping(source = "delegation.id", target = "delegationId")
    @Mapping(source = "delegation.name", target = "delegationName")
    MunicipalityDTO toDto(Municipality municipality);

    @Mapping(source = "delegationId", target = "delegation")
    Municipality toEntity(MunicipalityDTO municipalityDTO);

    default Municipality fromId(Long id) {
        if (id == null) {
            return null;
        }
        Municipality municipality = new Municipality();
        municipality.setId(id);
        return municipality;
    }
}
