package com.stayhome.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class OrganizationNotFoundException extends AbstractThrowableProblem {

    public OrganizationNotFoundException(Long organizationId) {
        super(ErrorConstants.ORGANIZATION_NOT_FOUND_TYPE,"Organization not found, organizationId = " + organizationId, Status.NOT_FOUND);
    }
}
