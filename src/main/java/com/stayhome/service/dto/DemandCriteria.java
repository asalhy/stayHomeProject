package com.stayhome.service.dto;

import com.stayhome.domain.enumeration.DemandStatus;

public class DemandCriteria {

    private DemandStatus status;
    private Long localityId;
    private String assignee;
    private Long serviceId;
    private String postalCode;

    public void setStatus(DemandStatus status) {
        this.status = status;
    }

    public DemandStatus getStatus() {
        return status;
    }

    public Long getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Long localityId) {
        this.localityId = localityId;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "DemandCriteriaVM{" +
            "status='" + getStatus() + "'" +
            ", assignee='" + getAssignee() + "'" +
            ", serviceId='" + getServiceId() + "'" +
            ", localityId='" + getLocalityId() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            "}";
    }
}
