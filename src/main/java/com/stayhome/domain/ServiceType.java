package com.stayhome.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A ServiceType.
 */
@Entity
@Table(name = "services")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ServiceType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "serviceTypes")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<Organization> organizations = new HashSet<>();

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

    public ServiceType name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public ServiceType organizations(Set<Organization> organizations) {
        this.organizations = organizations;
        return this;
    }

    public ServiceType addOrganization(Organization organization) {
        this.organizations.add(organization);
        organization.getServiceTypes().add(this);
        return this;
    }

    public ServiceType removeOrganization(Organization organization) {
        this.organizations.remove(organization);
        organization.getServiceTypes().remove(this);
        return this;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceType)) {
            return false;
        }
        return id != null && id.equals(((ServiceType) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ServiceType{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
