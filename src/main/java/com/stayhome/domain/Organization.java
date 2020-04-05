package com.stayhome.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Organization.
 */
@Entity
@Table(name = "organizations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "requires_approval", nullable = false)
    private Boolean requiresApproval;

    @NotNull
    @Column(name = "default_phone", nullable = false)
    private String defaultPhone;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "organizations_service_types",
               joinColumns = @JoinColumn(name = "organization_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "service_types_id", referencedColumnName = "id"))
    private Set<ServiceType> serviceTypes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Organization name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isRequiresApproval() {
        return requiresApproval;
    }

    public Organization requiresApproval(Boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
        return this;
    }

    public void setRequiresApproval(Boolean requiresApproval) {
        this.requiresApproval = requiresApproval;
    }

    public String getDefaultPhone() {
        return defaultPhone;
    }

    public Organization defaultPhone(String defaultPhone) {
        this.defaultPhone = defaultPhone;
        return this;
    }

    public void setDefaultPhone(String defaultPhone) {
        this.defaultPhone = defaultPhone;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Organization creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public Organization serviceTypes(Set<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
        return this;
    }

    public Organization addServiceTypes(ServiceType serviceType) {
        this.serviceTypes.add(serviceType);
        serviceType.getOrganizations().add(this);
        return this;
    }

    public Organization removeServiceTypes(ServiceType serviceType) {
        this.serviceTypes.remove(serviceType);
        serviceType.getOrganizations().remove(this);
        return this;
    }

    public void setServiceTypes(Set<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return id != null && id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Organization{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", requiresApproval='" + isRequiresApproval() + "'" +
            ", defaultPhone='" + getDefaultPhone() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
