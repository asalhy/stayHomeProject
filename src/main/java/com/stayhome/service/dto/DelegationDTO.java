package com.stayhome.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.stayhome.domain.Delegation} entity.
 */
public class DelegationDTO implements Serializable {

    private Long id;
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
}
