package com.stayhome.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

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

    @ManyToOne
    @JsonIgnoreProperties("serviceTypes")
    private Volunteer volunteer;

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

    public Volunteer getVolunteer() {
        return volunteer;
    }

    public ServiceType volunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
        return this;
    }

    public void setVolunteer(Volunteer volunteer) {
        this.volunteer = volunteer;
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
