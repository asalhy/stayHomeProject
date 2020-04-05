package com.stayhome.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.stayhome.domain.enumeration.DemandStatus;

/**
 * A DTO for the {@link com.stayhome.domain.DemandAudit} entity.
 */
public class DemandAuditDTO implements Serializable {
    
    private Long id;

    @NotNull
    private DemandStatus status;

    private String description;

    @NotNull
    private String ipAddress;

    @NotNull
    private LocalDate creationDate;


    private Long userId;

    private Long demandId;
    
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDemandId() {
        return demandId;
    }

    public void setDemandId(Long demandId) {
        this.demandId = demandId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DemandAuditDTO demandAuditDTO = (DemandAuditDTO) o;
        if (demandAuditDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demandAuditDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DemandAuditDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            ", userId=" + getUserId() +
            ", demandId=" + getDemandId() +
            "}";
    }
}
