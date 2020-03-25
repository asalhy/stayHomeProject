package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.DelegationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Delegation} and its DTO {@link DelegationDTO}.
 */
@Mapper(componentModel = "spring", uses = {GouvernoratMapper.class})
public interface DelegationMapper extends EntityMapper<DelegationDTO, Delegation> {

    @Mapping(source = "gouvernorat.id", target = "gouvernoratId")
    @Mapping(source = "gouvernorat.name", target = "gouvernoratName")
    DelegationDTO toDto(Delegation delegation);

    @Mapping(source = "gouvernoratId", target = "gouvernorat")
    Delegation toEntity(DelegationDTO delegationDTO);

    default Delegation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Delegation delegation = new Delegation();
        delegation.setId(id);
        return delegation;
    }
}
