package com.stayhome.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.stayhome.domain.Municipality} entity.
 */
public class MunicipalityDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;


    private Long delegationId;

    private String delegationName;
    
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

    public Long getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(Long delegationId) {
        this.delegationId = delegationId;
    }

    public String getDelegationName() {
        return delegationName;
    }

    public void setDelegationName(String delegationName) {
        this.delegationName = delegationName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MunicipalityDTO municipalityDTO = (MunicipalityDTO) o;
        if (municipalityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), municipalityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MunicipalityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", delegationId=" + getDelegationId() +
            ", delegationName='" + getDelegationName() + "'" +
            "}";
    }
}
