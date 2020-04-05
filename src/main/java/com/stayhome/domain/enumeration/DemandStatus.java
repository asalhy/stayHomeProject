package com.stayhome.domain.enumeration;

import java.util.Objects;

/**
 * The DemandStatus enumeration.
 */
public enum DemandStatus {
    OPEN("A user submitted a new demand"),
    ASSIGNED("ASSIGNED"),
    IN_PROCESS("IN_PROCESS"),
    DONE("DONE"),
    REJECTED("REJECTED"),
    CANCELLED("CANCELLED");

    final String description;

    private DemandStatus(String description) {
        this.description = Objects.requireNonNull(description);
    }

    public String getDescription() {
        return description;
    }
}
