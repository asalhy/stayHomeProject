package com.stayhome.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Delegation.
 */
@Entity
@Table(name = "delegations")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Delegation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    private Governorate governorate;

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

    public Delegation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Governorate getGovernorate() {
        return governorate;
    }

    public Delegation governorate(Governorate governorate) {
        this.governorate = governorate;
        return this;
    }

    public void setGovernorate(Governorate governorate) {
        this.governorate = governorate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Delegation)) {
            return false;
        }

        Delegation other = (Delegation) o;
        return Objects.equals(this.name, other.name) && Objects.equals(this.governorate, other.governorate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.governorate);
    }

    @Override
    public String toString() {
        return "Delegation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
