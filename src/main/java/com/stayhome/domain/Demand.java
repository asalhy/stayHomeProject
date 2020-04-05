package com.stayhome.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.stayhome.domain.enumeration.DemandStatus;

/**
 * A Demand.
 */
@Entity
@Table(name = "demands")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Demand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "description")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DemandStatus status;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "demand")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DemandAudit> demandAudits = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("demands")
    private Locality locality;

    @ManyToOne
    @JsonIgnoreProperties("demands")
    private User assignee;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("demands")
    private Organization organization;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("demands")
    private ServiceType serviceType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Demand firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Demand lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Demand phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public Demand email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public Demand description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DemandStatus getStatus() {
        return status;
    }

    public Demand status(DemandStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(DemandStatus status) {
        this.status = status;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Demand creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<DemandAudit> getDemandAudits() {
        return demandAudits;
    }

    public Demand demandAudits(Set<DemandAudit> demandAudits) {
        this.demandAudits = demandAudits;
        return this;
    }

    public Demand addDemandAudit(DemandAudit demandAudit) {
        this.demandAudits.add(demandAudit);
        demandAudit.setDemand(this);
        return this;
    }

    public Demand removeDemandAudit(DemandAudit demandAudit) {
        this.demandAudits.remove(demandAudit);
        demandAudit.setDemand(null);
        return this;
    }

    public void setDemandAudits(Set<DemandAudit> demandAudits) {
        this.demandAudits = demandAudits;
    }

    public Locality getLocality() {
        return locality;
    }

    public Demand locality(Locality locality) {
        this.locality = locality;
        return this;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public User getAssignee() {
        return assignee;
    }

    public Demand assignee(User user) {
        this.assignee = user;
        return this;
    }

    public void setAssignee(User user) {
        this.assignee = user;
    }

    public Organization getOrganization() {
        return organization;
    }

    public Demand organization(Organization organization) {
        this.organization = organization;
        return this;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public Demand serviceType(ServiceType serviceType) {
        this.serviceType = serviceType;
        return this;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Demand)) {
            return false;
        }
        return id != null && id.equals(((Demand) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Demand{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", description='" + getDescription() + "'" +
            ", status='" + getStatus() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
