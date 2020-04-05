package com.stayhome.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.stayhome.domain.Locality} entity.
 */
public class LocalityDTO implements Serializable {

    private Long id;
    private String name;
    private String postalCode;
    private Long delegationId;

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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(Long delegationId) {
        this.delegationId = delegationId;
    }
}
