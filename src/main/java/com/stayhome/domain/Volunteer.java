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

/**
 * A Volunteer.
 */
@Entity
@Table(name = "volunteers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Volunteer implements Serializable {

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
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "volunteer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ServiceType> serviceTypes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("volunteers")
    private Municipality municipality;

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

    public Volunteer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Volunteer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Volunteer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public Volunteer phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Volunteer creationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<ServiceType> getServiceTypes() {
        return serviceTypes;
    }

    public Volunteer serviceTypes(Set<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
        return this;
    }

    public Volunteer addServiceTypes(ServiceType serviceType) {
        this.serviceTypes.add(serviceType);
        serviceType.setVolunteer(this);
        return this;
    }

    public Volunteer removeServiceTypes(ServiceType serviceType) {
        this.serviceTypes.remove(serviceType);
        serviceType.setVolunteer(null);
        return this;
    }

    public void setServiceTypes(Set<ServiceType> serviceTypes) {
        this.serviceTypes = serviceTypes;
    }

    public Municipality getMunicipality() {
        return municipality;
    }

    public Volunteer municipality(Municipality municipality) {
        this.municipality = municipality;
        return this;
    }

    public void setMunicipality(Municipality municipality) {
        this.municipality = municipality;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Volunteer)) {
            return false;
        }
        return id != null && id.equals(((Volunteer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", creationDate='" + getCreationDate() + "'" +
            "}";
    }
}
