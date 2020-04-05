package com.stayhome.service.dto;

import com.stayhome.domain.enumeration.DemandStatus;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link com.stayhome.domain.DemandAudit} entity.
 */
public class DemandAuditDTO implements Serializable {

    private Long id;
    private DemandStatus status;
    private String description;
    private String ipAddress;
    private LocalDate creationDate;
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DemandStatus getStatus() {
        return status;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
