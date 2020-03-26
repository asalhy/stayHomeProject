package com.stayhome.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Municipality.
 */
@Entity
@Table(name = "municipalities")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Municipality implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @ManyToOne
    private Delegation delegation;

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

    public Municipality name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Delegation getDelegation() {
        return delegation;
    }

    public Municipality delegation(Delegation delegation) {
        this.delegation = delegation;
        return this;
    }

    public void setDelegation(Delegation delegation) {
        this.delegation = delegation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Municipality)) {
            return false;
        }

        Municipality other = (Municipality) o;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public String toString() {
        return "Municipality{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
