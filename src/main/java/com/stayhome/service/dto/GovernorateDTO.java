package com.stayhome.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.stayhome.domain.Governorate} entity.
 */
public class GovernorateDTO implements Serializable {

    private Long id;
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
}
