package com.stayhome.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UserNotFoundException extends AbstractThrowableProblem {

    public UserNotFoundException(Long userId) {
        super(ErrorConstants.USER_NOT_FOUND_TYPE,"User not found, userId = " + userId, Status.NOT_FOUND);
    }
}
