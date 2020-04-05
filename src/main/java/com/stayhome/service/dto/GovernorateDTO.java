package com.stayhome.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.stayhome.domain.Governorate} entity.
 */
public class GovernorateDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GovernorateDTO governorateDTO = (GovernorateDTO) o;
        if (governorateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), governorateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GovernorateDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
