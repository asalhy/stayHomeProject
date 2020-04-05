package com.stayhome.service.mapper;

import com.stayhome.domain.Delegation;
import com.stayhome.service.dto.DelegationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity {@link Delegation} and its DTO {@link DelegationDTO}.
 */
@Mapper(componentModel = "spring")
public interface DelegationMapper {

    @Mapping(source = "governorate.id", target = "governorateId")
    DelegationDTO toDto(Delegation delegation);
}
