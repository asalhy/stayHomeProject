package com.stayhome.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.stayhome.domain.ServiceType} entity.
 */
public class ServiceTypeDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;


    private Long volunteerId;
    
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

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceTypeDTO serviceTypeDTO = (ServiceTypeDTO) o;
        if (serviceTypeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), serviceTypeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ServiceTypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", volunteerId=" + getVolunteerId() +
            "}";
    }
}
