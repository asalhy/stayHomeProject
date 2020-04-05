package com.stayhome.domain;

import com.stayhome.domain.enumeration.DemandStatus;
import com.stayhome.util.IpAddressHelper;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
    private LocalDate creationDate = LocalDate.now();

    @NotNull
    @Column(name = "user", nullable = false)
    private String user;

    @ManyToOne(optional = false)
    @NotNull
    private Demand demand;

    public static DemandAudit createDemandAudit(Demand demand, DemandStatus status, String user) {
        DemandAudit audit = new DemandAudit();

        audit.setId(null);
        audit.setDemand(demand);
        audit.setCreationDate(LocalDate.now());
        audit.setDescription(status.getDescription());
        audit.setStatus(status);
        audit.setIpAddress(IpAddressHelper.getIpAddress());
        audit.setUser(user);

        return audit;
    }

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

    public String getUser() {
        return user;
    }

    public DemandAudit user(String user) {
        this.user = user;
        return this;
    }

    public void setUser(String user) {
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

        DemandAudit other = (DemandAudit) o;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString() {
        return "DemandAudit{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            ", ipAddress='" + getIpAddress() + "'" +
            ", user='" + getUser() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
