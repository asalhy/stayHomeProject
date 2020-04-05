package com.stayhome.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.stayhome.domain.Organization} entity.
 */
public class OrganizationDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Boolean requiresApproval;

    @NotNull
    private String defaultPhone;

    @NotNull
    private LocalDate creationDate;

    private Set<ServiceTypeDTO> serviceTypes = new HashSet<>();
    
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

    public Boolean isRequiresApproval() {
        return requiresApproval;
    }

    public void setRequiresApproval(Boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }

    public String getDefaultPhone() {
        return defaultPhone;
    }

    public void setDefaultPhone(String defaultPhone) {
        this.defaultPhone = defaultPhone;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<ServiceTypeDTO> getServiceTypes() {
        return serviceTypes;
    }

    public void setServiceTypes(Set<ServiceTypeDTO> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OrganizationDTO organizationDTO = (OrganizationDTO) o;
        if (organizationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organizationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganizationDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", requiresApproval='" + isRequiresApproval() + "'" +
            ", defaultPhone='" + getDefaultPhone() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", serviceTypes='" + getServiceTypes() + "'" +
            "}";
    }
}
