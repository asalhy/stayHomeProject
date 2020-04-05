package com.stayhome.service.mapper;


import com.stayhome.domain.*;
import com.stayhome.service.dto.DelegationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Delegation} and its DTO {@link DelegationDTO}.
 */
@Mapper(componentModel = "spring", uses = {GovernorateMapper.class})
public interface DelegationMapper extends EntityMapper<DelegationDTO, Delegation> {

    @Mapping(source = "governorate.id", target = "governorateId")
    DelegationDTO toDto(Delegation delegation);

    @Mapping(source = "governorateId", target = "governorate")
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
