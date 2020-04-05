package com.stayhome.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A DTO for the {@link com.stayhome.domain.Organization} entity.
 */
public class OrganizationDTO implements Serializable {

    @NotNull
    private Long id;

    private String name;
    private Boolean requiresApproval;
    private String defaultPhone;
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
}
