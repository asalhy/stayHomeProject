package com.stayhome.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.stayhome.domain.Delegation} entity.
 */
public class DelegationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;


    private Long governorateId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGovernorateId() {
        return governorateId;
    }

    public void setGovernorateId(Long governorateId) {
        this.governorateId = governorateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DelegationDTO delegationDTO = (DelegationDTO) o;
        if (delegationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), delegationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DelegationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", governorateId=" + getGovernorateId() +
            "}";
    }
}
