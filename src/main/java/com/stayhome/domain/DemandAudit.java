package com.stayhome.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

import com.stayhome.domain.enumeration.DemandStatus;

/**
 * A DemandAudit.
 */
@Entity
@Table(name = "demand_audits")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DemandAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DemandStatus status;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("demandAudits")
    private User user;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("demandAudits")
    private Demand demand;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DemandStatus getStatus() {
        return status;
    }

    public DemandAudit status(DemandStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public DemandAudit description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public DemandAudit ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public DemandAudit creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public DemandAudit user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Demand getDemand() {
        return demand;
    }

    public DemandAudit demand(Demand demand) {
        this.demand = demand;
        return this;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DemandAudit)) {
            return false;
        }
        return id != null && id.equals(((DemandAudit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DemandAudit{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
