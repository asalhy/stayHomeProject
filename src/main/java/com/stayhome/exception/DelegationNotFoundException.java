package com.stayhome.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class DelegationNotFoundException extends AbstractThrowableProblem {

    public DelegationNotFoundException(Long delegationId) {
        super(ErrorConstants.DELEGATION_NOT_FOUND_TYPE,"Delegation not found, delegationId = " + delegationId, Status.NOT_FOUND);
    }
}
